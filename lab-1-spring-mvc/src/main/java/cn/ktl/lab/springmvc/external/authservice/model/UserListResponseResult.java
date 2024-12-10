package cn.ktl.lab.springmvc.external.authservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

/**
 * @Author lin ho
 * Des:
 * {
 *         "firstname": "U1tN",
 *         "lastname": "nmeq",
 *         "email": "yu.zhang@centific.com",
 *         "phone": "62746549909",
 *         "extradata": {
 *             "sub_tenant": "oneformacommonservice"
 *         },
 *         "roleid": null,
 *         "isactive": true,
 *         "loginuid": "HXasOj5",
 *         "password_expired": false,
 *         "password_set_on": "2024-11-25",
 *         "social_login": false
 *     }
 */
@Setter
@Getter
public class UserListResponseResult {

    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private Map<String,String> extradata;

    private String roleid;

    private Boolean isactive;
    private String loginuid;

    @JsonAlias("password_expired")
    private Boolean passwordExpired;

    @JsonAlias("password_set_on")
    private Date passwordSetOn;

    @JsonAlias("social_login")
    private Boolean socialLogin;
}
