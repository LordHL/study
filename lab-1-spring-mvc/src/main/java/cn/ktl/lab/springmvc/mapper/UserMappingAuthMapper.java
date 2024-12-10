package cn.ktl.lab.springmvc.mapper;

import cn.hutool.core.collection.CollUtil;
import cn.ktl.lab.springmvc.model.UserMappingAuth;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author lin ho
 * Des: UserMappingAuthMapper
 */
@Mapper
public interface UserMappingAuthMapper extends BaseMapper<UserMappingAuth> {
    /**
     * 查询 auth login uid
     * @param userId userId
     * @return auth login uid
     */
    default UserMappingAuth selectByUserIdUserMappingAuth(Long userId){
        LambdaQueryWrapper<UserMappingAuth> queryWrapper = Wrappers.lambdaQuery(UserMappingAuth.class);
        queryWrapper.eq(UserMappingAuth::getUserId,userId);
        List<UserMappingAuth> userMappingAuths = selectList(queryWrapper);
        if (CollUtil.isEmpty(userMappingAuths)){
            return null;
        }else {
            return userMappingAuths.get(0);
        }
    }
}
