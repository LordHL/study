package cn.ktl.lab.springmvc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import static cn.ktl.lab.springmvc.constant.SecurityConstants.REFRESH_TOKEN_EXPIRATION_TIME;
import static cn.ktl.lab.springmvc.constant.SecurityConstants.TOKEN_EXPIRATION_TIME;


@Configuration
@ConfigurationProperties(prefix = "auth.service")
@Data
public class AuthSvcConfig {

    private String baseUrl;

    private String clientId;

    private String clientSecret;

    private String subTenant;

    /**
     * token 过期时间 默认9小时
     */
    private Long tokenExpireTime = TOKEN_EXPIRATION_TIME;

    /**
     * refreshToken 过期时间 默认23小时
     */
    private Long refreshTokenExpireTime = REFRESH_TOKEN_EXPIRATION_TIME;

    /**
     * 256位 随机密钥
     */
    private String recoveryKeySecret;


}
