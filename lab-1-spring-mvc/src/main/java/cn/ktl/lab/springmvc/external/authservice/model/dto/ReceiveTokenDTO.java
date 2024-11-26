package cn.ktl.lab.springmvc.external.authservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author: yue.ma12@centific.com
 * Des:{
 * "access_token": "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9...",
 * "auth_req_id": "id-PqtPRAhoC7QJe9VIN"
 * }
 */

@Data
public class ReceiveTokenDTO {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("auth_req_id")
    private String authReqId;
}
