package cn.ktl.lab.springmvc.external.authservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @Author lin ho
 * Des:
 * {
 *   "refresh_token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJsaWQiOiJGMTIzNDU2MTIiLCJleHAiOjE3MzA5NjMxNDR9.prjDg0WtCswFnOyYkyJo58MxjRGmxhs_DHUSwyy67XU"
 * }
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RefreshAccessTokenDTO {

    @JsonProperty("refresh_token")
    private String refreshToken;
}
