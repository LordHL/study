package cn.ktl.lab.springmvc.external.authservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author lin ho
 * Des: TODO
 */
@Data
public class AuthDeleteUserDTO {

    @JsonProperty("login_uid")
    private String loginUid;

}
