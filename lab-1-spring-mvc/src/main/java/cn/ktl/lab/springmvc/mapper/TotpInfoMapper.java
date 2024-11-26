package cn.ktl.lab.springmvc.mapper;

import cn.ktl.lab.springmvc.model.TotpInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lin ho
 * Des: 用户 totp
 */
@Mapper
public interface TotpInfoMapper extends BaseMapper<TotpInfo> {

    /**
     * 通过email 查询用户的totp 信息
     * @param email email
     * @return  TotpInfo
     */
    default TotpInfo getByEmailTotpInfo(String email){
        LambdaQueryWrapper<TotpInfo> queryWrapper = Wrappers.lambdaQuery(TotpInfo.class);
        queryWrapper.eq(TotpInfo::getEmail,email);
        return selectOne(queryWrapper);
    }
}
