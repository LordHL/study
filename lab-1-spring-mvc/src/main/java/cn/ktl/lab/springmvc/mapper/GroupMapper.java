package cn.ktl.lab.springmvc.mapper;

import cn.ktl.lab.springmvc.model.Group;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mingquan.liu
 * @description 针对表【um_group】的数据库操作Mapper
 * @createDate 2024-08-22 17:03:32
 * @Entity com.oneforma.usermanagement.model.Group
 */
@Mapper
public interface GroupMapper extends BaseMapper<Group> {


}




