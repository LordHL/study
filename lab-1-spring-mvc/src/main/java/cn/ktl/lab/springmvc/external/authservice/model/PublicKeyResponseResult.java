package cn.ktl.lab.springmvc.external.authservice.model;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author lin ho
 * Des: 从auth获取public key
 */
@Setter
@Getter
public class PublicKeyResponseResult extends AuthSvcResponseResult{

    /**
     * public key
     */
    private String key;
}
