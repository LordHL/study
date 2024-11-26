package cn.ktl.lab.springmvc.external.authservice.model.vo;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * CreateNewUser result
 */
@Data
public class AuthSvcUserVO {
    /**
     * Email
     */
    @JsonAlias("email")
    private String email;
    /**
     * Extra data
     */
    @JsonAlias("extra_data")
    private Map<String, Object> extraData = new HashMap<>();
    /**
     * First name
     */
    @JsonAlias("first_name")
    private String firstName;
    /**
     * Last name
     */
    @JsonAlias("last_name")
    private String lastName;
    /**
     * Is active
     */
    @JsonAlias("is_active")
    private boolean isActive;
    /**
     * Login uid
     */
    @JsonAlias("login_uid")
    private String loginUid;
    /**
     * Password
     */
    @JsonAlias("password")
    private String password;
    /**
     * Phone
     */
    @JsonAlias("phone")
    private String phone;
    /**
     * Role id
     */
    @JsonAlias("role_id")
    private String roleId;
}
