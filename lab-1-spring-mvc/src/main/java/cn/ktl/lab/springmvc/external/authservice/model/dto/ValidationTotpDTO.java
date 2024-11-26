package cn.ktl.lab.springmvc.external.authservice.model.dto;

import lombok.*;

/**
 * @Author lin ho
 * Des: 二次验证 totp
 * {
 *   "email": "user@centific.com",
 *   "totp": "443195",
 *   "login": true
 * }
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationTotpDTO {

    /**
     * email
     */
    private String email;

    /**
     * app 端的code
     */
    private String totp;

    /**
     * 默认是需要登录
     */
    private Boolean login = Boolean.TRUE;
}
