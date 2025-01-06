package cn.ktl.lab.springmvc.dto;


import cn.ktl.lab.springmvc.base.aop.CountryCodeToNameMapper;
import cn.ktl.lab.springmvc.base.aop.LanguageCodeToValueMapper;
import cn.ktl.lab.springmvc.base.aop.MapField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author lin ho
 * Des: TODO
 */
@Setter
@Getter
public class UserDTO {

    private Long id;

    private String userNo;

    private String username;

    private String password;

    private String phone;


    private Integer userType;

    private String email;

    private Integer userStatus;

    @MapField(mapperClass = CountryCodeToNameMapper.class)
    private String countryOfResidence;

    private String fraudScore;

    @MapField(mapperClass = LanguageCodeToValueMapper.class)
    private String language;

    private String fraudChance;

    private Integer isFraud;

    private List<String> roles;

}
