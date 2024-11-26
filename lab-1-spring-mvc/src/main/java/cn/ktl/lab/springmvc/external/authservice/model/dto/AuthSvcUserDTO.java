package cn.ktl.lab.springmvc.external.authservice.model.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * CreateNewUser
 */
@Data
public class AuthSvcUserDTO {
    /**
     * Email
     */
    @JsonProperty("email")
    private String email;
    /**
     * Extra data
     */
    @JsonProperty("extra_data")
    private Map<String, Object> extraData = new HashMap<>();
    /**
     * First name
     */
    @JsonProperty("first_name")
    private String firstName;
    /**
     * Last name
     */
    @JsonProperty("last_name")
    private String lastName;
    /**
     * Is active
     */
    @JsonProperty("is_active")
    private boolean isActive;
    /**
     * Login uid
     */
    @JsonProperty("login_uid")
    private String loginUid;
    /**
     * Password
     */
    @JsonProperty("password")
    private String password;
    /**
     * Phone
     */
    @JsonProperty("phone")
    private String phone;
    /**
     * Role id
     */
    @JsonProperty("role_id")
    private String roleId;
}
