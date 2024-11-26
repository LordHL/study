package cn.ktl.lab.springmvc.external.authservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
public class LoginResponseResult extends AuthSvcResponseResult{

    @JsonAlias("access_token")
    private String accessToken;

    @JsonAlias("refresh_token")
    private String refreshToken;

    @JsonAlias("expires_at")
    private Date expiresAt;
}
