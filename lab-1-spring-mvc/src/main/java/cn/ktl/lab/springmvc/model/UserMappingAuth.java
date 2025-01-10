package cn.ktl.lab.springmvc.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * packageName com.oneforma.usermanagement.model
 *
 * @author linho
 * @date 2024/11/1
 * @description UserMappingEmployee
 */
@Data
@TableName("um_user_mapping_auth")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserMappingAuth implements Serializable {
    private static final long serialVersionUID = -12L;

    @TableId(value = "user_id")
    private Long userId;

    @TableField(value = "auth_login_uid")
    private String authLoginUid;
}
