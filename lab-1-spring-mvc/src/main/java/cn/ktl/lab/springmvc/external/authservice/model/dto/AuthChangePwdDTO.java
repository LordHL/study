package cn.ktl.lab.springmvc.external.authservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author lin ho
 * Des: AuthChangePwdDTO
 * ex:
 * {
 *   "old_password": "a123456",
 *   "new_password": "Admin2018",
 *   "confirm_password": "Admin2018",
 *   "login_uid": "F12345612"
 * }
 */
@Setter
@Getter
public class AuthChangePwdDTO {

    @JsonProperty("old_password")
    private String oldPassword;

    @JsonProperty("new_password")
    private String newPassword;

    @JsonProperty("confirm_password")
    private String confirmPassword;

    @JsonProperty("login_uid")
    private String loginUid;
}
