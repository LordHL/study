package cn.ktl.lab.springmvc.external.authservice.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author lin ho
 * Des: 生成邮件验证码的实体
 *
 * {
 *   "email": "lin.he@centific.com",
 *   "otp": "166420"
 * }
 */

@Setter
@Getter
public class ValidationOtpDTO extends GenerateValidationOtpDTO{



    /**
     * otp code
     */
    private String otp;
}
