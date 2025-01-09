package cn.ktl.lab.springmvc.model;

import cn.ktl.lab.springmvc.base.db.EncryptTypeHandler;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author lin ho
 * Des: 用户 totp
 */

@Data
//@TableName("um_totp_info")
@TableName(value = "um_totp_info",autoResultMap = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotpInfo {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "email")
    private String email;

//    @TableField(value = "qr_image")
    @TableField(value = "qr_image",typeHandler = EncryptTypeHandler.class)
    private String qrImage;


//    @TableField(value = "recovery_key")
    @TableField(value = "recovery_key",typeHandler = EncryptTypeHandler.class)
    private String recoveryKey;


//    @TableField(value = "totp_key")
    @TableField(value = "totp_key",typeHandler = EncryptTypeHandler.class)
    private String totpKey;

    /**
     * 是否绑定了MFA， 0 - false，2 - true
     */
    @TableField(value = "binding_mfa")
    private Integer bindingMfa;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "totp_info")
    private Integer totpInfo = 1;

}
