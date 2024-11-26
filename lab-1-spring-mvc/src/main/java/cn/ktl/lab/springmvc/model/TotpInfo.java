package cn.ktl.lab.springmvc.model;

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
@TableName("um_totp_info")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TotpInfo {


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "email")
    private String email;

    @TableField(value = "qr_image")
    private String qrImage;


    @TableField(value = "recovery_key")
    private String recoveryKey;


    @TableField(value = "totp_key")
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
