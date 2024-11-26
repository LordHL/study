package cn.ktl.lab.springmvc.external.authservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author lin ho
 * Des: 注册的用户信息
 */
@Setter
@Getter
public class RegisterResponseResult<T> extends AuthSvcResponseResult<T>{

    @JsonAlias("qr_image")
    private String qrImage;

    @JsonAlias("recovery_key")
    private String recoveryKey;

    @JsonAlias("totp_key")
    private String totpKey;
}
