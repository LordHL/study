package cn.ktl.lab.springmvc.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cn.ktl.lab.springmvc.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author lin ho
 * Des: TODO
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
