package cn.ktl.lab.springmvc.external.authservice.model.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author lin ho
 * Des: 开启totp的注册用户
 * {
 *   "username": "lin1.he@centific.com",
 *   "password": "a123456"
 * }
 */
@Setter
@Getter
public class RegisterUserTotpDTO {

    /**
     * 用户名 - email
     */
    private String username;

    /**
     * 密码
     */
    private String password;
}
