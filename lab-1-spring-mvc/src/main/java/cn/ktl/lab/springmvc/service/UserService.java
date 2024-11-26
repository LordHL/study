package cn.ktl.lab.springmvc.service;

/**
 * @Author lin ho
 * Des: TODO
 */

import cn.hutool.core.collection.CollUtil;
import cn.ktl.lab.springmvc.config.AuthSvcConfig;
import cn.ktl.lab.springmvc.enums.UserTypeDefaultGroupEnum;
import cn.ktl.lab.springmvc.enums.UserTypeEnum;
import cn.ktl.lab.springmvc.exception.BusinessException;
import cn.ktl.lab.springmvc.external.authservice.model.RegisterResponseResult;
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
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import static cn.ktl.lab.springmvc.exception.UmErrorCodeEnum.UM_USER_EMAIL_HAS_EXIST;

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

    @Value("${auth.service.recovery-key-secret:z/yAKXDdpwtCkfIDSYCkHu8z5dSbglS3Ovk628oTlGQ=}")
    private String recoveryKeySecret;

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


}
