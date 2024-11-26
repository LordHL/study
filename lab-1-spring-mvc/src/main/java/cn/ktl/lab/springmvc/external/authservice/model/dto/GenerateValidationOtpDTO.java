package cn.ktl.lab.springmvc.external.authservice.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author lin ho
 * Des: 基于email生成 otp code
 */
@Setter
@Getter
public class GenerateValidationOtpDTO {
    /**
     * email
     */
    private String email;
}
