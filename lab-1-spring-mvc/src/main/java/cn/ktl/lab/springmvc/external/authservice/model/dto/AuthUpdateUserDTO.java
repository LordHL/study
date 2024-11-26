package cn.ktl.lab.springmvc.external.authservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lin ho
 * Des: AuthUpdateUserDTO
 * {
 *   "email": "lin.he@centific.com",
 *   "first_name": "lin1",
 *   "last_name": "lin2",
 *   "phone": "12388900989",
 *   "is_active": true,
 *   "extra_data": {
 *     "sub_tenant": "oneformacommonservice"
 *   },
 *   "role_id": ""
 * }
 */
@Setter
@Getter
public class AuthUpdateUserDTO {

    /**
     * Email
     */
    @JsonProperty("email")
    private String email;

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
     * Phone
     */
    @JsonProperty("phone")
    private String phone;

    /**
     * Is active
     */
    @JsonProperty("is_active")
    private boolean isActive;

    /**
     * Extra data
     */
    @JsonProperty("extra_data")
    private Map<String, Object> extraData = new HashMap<>();

    /**
     * Role id
     */
    @JsonProperty("role_id")
    private String roleId;
}
