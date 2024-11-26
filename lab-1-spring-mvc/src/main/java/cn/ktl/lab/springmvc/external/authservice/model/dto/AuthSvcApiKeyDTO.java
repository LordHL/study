package cn.ktl.lab.springmvc.external.authservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 请求 api-key 的参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthSvcApiKeyDTO {

    @JsonProperty("client_id")
    private String clientId;

    @JsonProperty("client_secret")
    private String clientSecret;
}
