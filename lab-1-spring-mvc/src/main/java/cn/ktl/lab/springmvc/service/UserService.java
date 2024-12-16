package cn.ktl.lab.springmvc.service;

/**
 * @Author lin ho
 * Des: TODO
 */

import cn.hutool.core.collection.CollUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.ktl.lab.springmvc.config.AuthSvcConfig;
import cn.ktl.lab.springmvc.enums.UserTypeDefaultGroupEnum;
import cn.ktl.lab.springmvc.enums.UserTypeEnum;
import cn.ktl.lab.springmvc.exception.BusinessException;
import cn.ktl.lab.springmvc.external.authservice.model.RegisterResponseResult;
import cn.ktl.lab.springmvc.external.authservice.model.UserListResponseResult;
import cn.ktl.lab.springmvc.external.authservice.model.dto.AuthDeleteUserDTO;
import cn.ktl.lab.springmvc.external.authservice.model.dto.AuthSvcUserDTO;
import cn.ktl.lab.springmvc.external.authservice.model.vo.AuthSvcUserVO;
import cn.ktl.lab.springmvc.external.authservice.service.AuthSvcUserService;
import cn.ktl.lab.springmvc.mapper.*;
import cn.ktl.lab.springmvc.model.*;
import cn.ktl.lab.springmvc.utils.*;
import cn.ktl.lab.springmvc.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import kotlin.jvm.internal.Lambda;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static cn.ktl.lab.springmvc.exception.UmErrorCodeEnum.UM_USER_EMAIL_HAS_EXIST;
import static com.fasterxml.jackson.databind.type.LogicalType.Collection;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService extends ServiceImpl<UserMapper, User> {

    private final UserMapper userMapper;
    private final UserGroupMapper userGroupMapper;
    private final GroupMapper groupMapper;
    private final TotpInfoMapper totpInfoMapper;
    private final EmployeeMapper employeeMapper;

    private final SnowflakeIdUtils snowflakeIdUtils;
    private final PasswordEncoder passwordEncoder;
    private final AuthSvcUserService authSvcUserService;
    private final AuthSvcConfig authSvcConfig;

    private final UserMappingAuthMapper userMappingAuthMapper;

    @Value("${auth.service.recovery-key-secret:z/yAKXDdpwtCkfIDSYCkHu8z5dSbglS3Ovk628oTlGQ=}")
    private String recoveryKeySecret;


    private final  StringRedisTemplate stringRedisTemplate;
    public UserVO get(Integer id) {
        return new UserVO().setId(id).setUsername("test");
    }

    @Transactional
    public void createUser(RegisterUserBO userBO) {
        //0.校验密码格式
        PasswordValidatorUtils.isValidPassword(userBO.getPassword());
        //1.创建user
        checkEmailIsExist(userBO.getEmail());
        if (userBO.getId() == null){
            userBO.setId(snowflakeIdUtils.nextId());
        }

        User user = BeanConvertUtils.baseConvert(userBO, User.class);
        LocalDateTime now = LocalDateTime.now(ZoneOffset.UTC);
        user.setLastPasswordResetTime(now);
        user.setPassword(passwordEncoder.encode(userBO.getPassword()));
        user.setRegistrationTime(now);
        user.setUserStatus(1);
        user.setFullName(userBO.getFirstName() + " " + userBO.getLastName());
        user.setUpdatedTime(now);
        user.setUpdatedBy("1");
        save(user);
        log.info("创建用户到um_user表：{}", JsonUtils.toJsonPrettyString(user));

        if (UserTypeEnum.EMPLOYEES.getValue() == userBO.getUserType()) {
            Employee employee = BeanConvertUtils.baseConvert(user, Employee.class);
            log.info("employee info :{}", JsonUtils.toJsonPrettyString(employee));
            int insert = employeeMapper.insert(employee);
            log.info("employee insert count = {}", insert);
        }

        try {
            LambdaQueryWrapper<Group> superAdmin = Wrappers.<Group>lambdaQuery().eq(Group::getGroupName, "superAdmin");
            Group group = groupMapper.selectOne(superAdmin);
            if (group != null){
                UserGroup userGroup = UserGroup.builder()
                        .groupId(group.getId())
                        .userId(user.getId())
                        .build();
                userGroupMapper.insert(userGroup);
            }
        }catch (Exception e){
            log.error("init employee superAdmin group fail : ",e);
        }

        try {
            // user_type --> group name --> group
            Group group = getDefaultGroupByUserType(user.getUserType());
            if (group != null) {
                UserGroup userGroup = UserGroup.builder()
                        .groupId(group.getId())
                        .userId(user.getId())
                        .build();
                userGroupMapper.insert(userGroup);
            } else {
                log.error("user: userId{} can not mapping default group", user.getId());
            }
        } catch (Exception e) {
            log.error("UserId = {}添加用户组失败：", user.getId(), e);
        }
        //3.1 call auth
        AuthSvcUserDTO dto = new AuthSvcUserDTO();
        dto.setEmail(user.getEmail());
        dto.setPassword(userBO.getPassword());
        dto.setRoleId("");
        dto.setLoginUid(user.getUserNo());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPhone(user.getPhone());
        dto.setActive(true);
        dto.getExtraData().put("sub_tenant", authSvcConfig.getSubTenant());
        RegisterResponseResult<AuthSvcUserVO> newUser = authSvcUserService.createNewUser(dto);
        //3.2 store qr code
        TotpInfo totpInfo = TotpInfo.builder()
                .email(user.getEmail())
                .qrImage(newUser.getQrImage())
                .totpKey(newUser.getTotpKey())
                .userId(user.getId())
                .bindingMfa(0)
                .build();
        //加密
        totpInfo.setRecoveryKey(SecureEncryptionUtils.aesEncryption(recoveryKeySecret, newUser.getRecoveryKey()));
        totpInfoMapper.insert(totpInfo);

    }

    /**
     * 根据用户类型， 获取默认的groupId
     *
     * @param userType
     * @return
     */
    private Group getDefaultGroupByUserType(Integer userType) {
        UserTypeDefaultGroupEnum userTypeEnumByUserType = UserTypeDefaultGroupEnum.getUserTypeEnumByUserType(userType);
        String groupName = userTypeEnumByUserType.getGroupName();
        return groupMapper.selectOne(Wrappers.lambdaQuery(Group.class).eq(Group::getGroupName, groupName));
    }

    public void checkEmailIsExist(String email) {
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery();
        queryWrapper.eq(User::getEmail, email);
        User user = userMapper.selectOne(queryWrapper);
        if (user != null) {
            throw BusinessException.of(UM_USER_EMAIL_HAS_EXIST);
        }
    }


    public void batchCreateUsers(BatchCreateUserBO batchCreateUserBO){
        List<RegisterUserBO> users = batchCreateUserBO.getUsers();
        if (CollUtil.isEmpty(users)){
            return;
        }

        for (RegisterUserBO bo : users){
            String email = bo.getEmail();
            String username = email.split("@")[0]; // 获取 @ 之前的部分
            long l = snowflakeIdUtils.nextId();
//            Long id
        }

    }

    @Transactional
    public void batchDeleteUser(List<String> emails){
        for (String email : emails){
            getSelf().deleteUser(email);
        }
    }

    @Transactional
    public void deleteUser(String email){
        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery();
        queryWrapper.eq(User::getEmail, email);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            log.info("user email = {} not exist ",email);
            return;
        }
        //1. 删除um_user
        userMapper.deleteById(user);
        UserMappingAuth userMappingAuth = userMappingAuthMapper.selectByUserIdUserMappingAuth(user.getId());
        String loginUid ;
        if (userMappingAuth == null){
            loginUid  = user.getUserNo();
        }else {
            loginUid = userMappingAuth.getAuthLoginUid();
        }
        AuthDeleteUserDTO authDeleteUserDTO = new AuthDeleteUserDTO();
        authDeleteUserDTO.setLoginUid(loginUid);
        authSvcUserService.deleteUser(authDeleteUserDTO);
        //2.删除um_employee
        LambdaQueryWrapper<Employee> queryWrapper1 = Wrappers.<Employee>lambdaQuery();
        queryWrapper1.eq(Employee::getEmail, email);
        Employee employee = employeeMapper.selectOne(queryWrapper1);
        if (employee != null){
            employeeMapper.deleteById(employee);
        }

        //3.删除用户关联的组
        LambdaQueryWrapper<UserGroup> q2 = Wrappers.lambdaQuery();
        q2.eq(UserGroup::getUserId,user.getId());
        userGroupMapper.delete(q2);

    }

    private UserService getSelf() {
        return SpringUtil.getBean(getClass());
    }


    public void handleUserMappingAuth() {
        // Step 1: 获取所有用户数据
        List<UserListResponseResult> userListResponseResults = authSvcUserService.listUser();

        // Step 2: 筛选 socialLogin 为 true 的数据
        List<UserListResponseResult> socialLoginUsers = userListResponseResults.stream()
                .filter(UserListResponseResult::getSocialLogin)
                .collect(Collectors.toList());

        // 筛选 socialLogin 为 false 的用户
        List<UserListResponseResult> nonSocialLoginUsers = userListResponseResults.stream()
                .filter(user -> !user.getSocialLogin())
                .collect(Collectors.toList());

        // 获取 socialLoginUsers 的 email 集合
        Set<String> socialLoginEmails = socialLoginUsers.stream()
                .map(UserListResponseResult::getEmail)
                .collect(Collectors.toSet());

        // Step 3: 根据 email 分组，筛选出 email 重复的用户
        List<UserListResponseResult> differentEmailUsers = nonSocialLoginUsers.stream()
                .filter(user -> !socialLoginEmails.contains(user.getEmail())) // 查找 email 相同的非 socialLogin 用户
                .collect(Collectors.toList());

        List<String> emails = differentEmailUsers.stream().map(UserListResponseResult::getEmail).collect(Collectors.toList());
        LambdaQueryWrapper<User> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.in(User::getEmail,emails);

        Map<String, UserListResponseResult> emailMap = differentEmailUsers.stream().collect(Collectors.toMap(UserListResponseResult::getEmail, Function.identity()));
        List<User> users = userMapper.selectList(queryWrapper);
        if (CollUtil.isNotEmpty(users)){
            for (User user : users){
                UserMappingAuth userMappingAuth = new UserMappingAuth(user.getId(), emailMap.get(user.getEmail()).getLoginuid());
                log.info("UserMappingAuth insert :{}",JsonUtils.toJsonPrettyString(userMappingAuth));
                userMappingAuthMapper.insert(userMappingAuth);
            }
        }
    }

    private final DynamicEmployeeMapper dynamicEmployeeMapper;

    public void queryEmployeeIdOrderDesc(){
        LambdaQueryWrapper<DynamicEmployee> queryWrapper = Wrappers.lambdaQuery();
        List<DynamicEmployee> dynamicEmployees = dynamicEmployeeMapper.selectList(queryWrapper);
        List<Integer> userNos = new ArrayList<>(5000);
        for (DynamicEmployee dynamicEmployee : dynamicEmployees){
            String employeeId = dynamicEmployee.getEmployeeId();
            //P9027660
            String num = employeeId.substring(1);
            String addOne = 1 + num;
            log.info("addOne :{}",addOne);
            Integer u = Integer.valueOf(addOne);
            userNos.add(u);
        }
        List<Integer> collect = userNos.stream().sorted().collect(Collectors.toList());
        collect.forEach(System.out::println);
    }


    public String getRedisKey(){
        stringRedisTemplate.opsForValue().set("test","123");
        String string = stringRedisTemplate.opsForValue().get("test");
        return string;
    }

}
