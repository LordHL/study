package cn.ktl.lab.springmvc.mapper;

import cn.ktl.lab.springmvc.model.Group;
import cn.ktl.lab.springmvc.model.UserGroup;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @Author lin ho
 * Des: TODO
 */
@Mapper
public interface UserGroupMapper extends BaseMapper<UserGroup> {


    @Select("select g.* from um_group g join um_user_group ug on g.id = ug.group_id " +
            "and g.group_status = 1 AND g.is_deleted = 0 " +
            "join um_user u on u.id = ug.user_id where u.id = #{userId} ")
    List<Group> getGroupsByUserId(@Param("userId") Long userId);

    @Select("select user_id from um_user_group  where group_id = #{groupId} ")
    List<Long> getUserIdsByGroupId(@Param("groupId") Long groupId);

}
