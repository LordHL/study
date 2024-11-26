package cn.ktl.lab.springmvc.external.authservice.model.dto;

import lombok.Data;

/**
 * Login
 */
@Data
public class AuthSvcUserLoginDTO {
    /**
     * Username
     */
    private String username;

    /**
     * Password
     */
    private String password;

}
