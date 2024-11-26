package cn.ktl.lab.springmvc.external.authservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author lin ho
 * Des: SocialLoginDTO
 * {
 *   "social_provider": "microsoft",
 *   "user_token": "eyJ0...",
 *   "enterprise_login": true
 * }
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocialLoginDTO {

    @JsonProperty("social_provider")
    private String socialProvider;

    @JsonProperty("user_token")
    private String userToken;

    @JsonProperty("enterprise_login")
    private Boolean enterpriseLogin = Boolean.TRUE;
}
