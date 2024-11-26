package cn.ktl.lab.springmvc.external.authservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiKeyResponseResult extends AuthSvcResponseResult {

    @JsonAlias("api_key")
    private String apiKey;

    @JsonAlias("expires_at")
    private Date expiresAt;
}
