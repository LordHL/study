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
 * @description 用户实体
 * @date 2020-11-29
 */
@Data
@TableName(value = "um_user")
//@TableName(value = "um_user",autoResultMap = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_no")
    private String userNo;

    @TableField(value = "username")
    private String username;

    @TableField(value = "full_name")
    private String fullName;

//    @TableField(value = "first_name",typeHandler = EncryptTypeHandler.class)
    @TableField(value = "first_name")
    private String firstName;

    @TableField(value = "last_name")
    private String lastName;


    /**
     * 10,crowd
     * 20,professionals
     * 30,agencies
     * 40,employees
     */
    @TableField(value = "user_type")
    private Integer userType;

    @TableField(value = "email")
    private String email;

    @TableField(value = "phone")
    private String phone;

    @TableField(value = "country_of_residence")
    private String countryOfResidence;


    @TableField(value = "language")
    private String language;

    /**
     * 用户状态： 0-Inactive，1-Active）
     */
    @TableField(value = "user_status")
    private Integer userStatus;

    @TableField(value = "fraud_score")
    private String fraudScore;

    @TableField(value = "is_fraud")
    private Integer isFraud;

    @TableField(value = "fraud_chance")
    private String fraudChance;

    @TableField(value = "fraud_update_time")
    private LocalDateTime fraudUpdateTime;

    @TableField(value = "password")
    private String password;

    @TableField(value = "last_password_reset_time")
    private LocalDateTime lastPasswordResetTime;

    @TableField(value = "registration_time")
    private LocalDateTime registrationTime;

    @TableField(value = "updated_time",fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedTime;

    @TableField(value = "updated_by",fill = FieldFill.INSERT_UPDATE)
    private String updatedBy;

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
