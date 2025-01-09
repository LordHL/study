package cn.ktl.lab.springmvc.model;

import cn.ktl.lab.springmvc.base.db.EncryptTypeHandler;
import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;


/**
 * @description 用户-内部员工 -实体
 */
@Data
@TableName("um_employee")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Employee implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_no")
    private String userNo;

    @TableField(value = "user_manage_no")
    private String userManageNo;

    @TableField(value = "username")
    private String username;

    @TableField(value = "first_name", typeHandler = EncryptTypeHandler.class)
    private String firstName;

    @TableField(value = "last_name", typeHandler = EncryptTypeHandler.class)
    private String lastName;


    @TableField(value = "email")
    private String email;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "time_zone")
    private String timeZone;

    /**
     * 用户状态： 0-Inactive，1-Active）
     */
    @TableField(value = "user_status")
    private Integer userStatus;

    @TableField(value = "registration_time")
    private LocalDateTime registrationTime;


    @TableField(value = "deleted_by",fill = FieldFill.INSERT_UPDATE)
    private String deletedBy;

    @TableField(value = "deleted_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime deletedTime;

    /**
     * 删除标识
     **/
    @TableField(value = "is_deleted",fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDeleted = 0;

}
