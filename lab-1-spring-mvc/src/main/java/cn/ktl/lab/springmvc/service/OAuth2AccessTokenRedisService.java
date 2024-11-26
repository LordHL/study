package cn.ktl.lab.springmvc.service;


import cn.ktl.lab.springmvc.constant.RedisKeyConstants;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static cn.ktl.lab.springmvc.constant.RedisKeyConstants.*;


/**
 * @Author lin ho
 * Des: 用户 缓存
 */

@Component
public class OAuth2AccessTokenRedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;


    /** ----------------------------------------x-api-key ------------------------------------------------- */
    /**
     * @return api-key
     */
    public String getApiKey() {
        return stringRedisTemplate.opsForValue().get(RedisKeyConstants.AUTH_SERVICE_API_KEY);
    }

    /**
     * 缓存api-key
     *
     * @param apiKey apiKey
     */
    public void setApiKey(String apiKey) {
        stringRedisTemplate.opsForValue().set(RedisKeyConstants.AUTH_SERVICE_API_KEY, apiKey, Duration.ofHours(7));
    }





    /** --------------------------------------public key--------------------------------------------------- */


    /**
     * 设置 公钥缓存 默认 8个小时
     *
     * @param publicKey publicKey
     */
    public void setPublicKey(String publicKey) {
        stringRedisTemplate.opsForValue().set(VERIFY_TOKEN_PUBLIC_KEY, publicKey, 8, TimeUnit.HOURS);
    }

    /**
     * 获取公钥
     *
     * @return publicKey
     */
    public String getPublicKey() {
        return stringRedisTemplate.opsForValue().get(VERIFY_TOKEN_PUBLIC_KEY);
    }

    /** --------------------------------------mfa binding status--------------------------------------------------- */

    /**
     * 添加mfa缓存绑定
     *
     * @param userId userId
     */
    public void setMfaBindingStatus(Long userId) {
        String key = String.format(MFA_BINDING_STATUS_KEY, userId);
        stringRedisTemplate.opsForValue().set(key, userId.toString(), 8, TimeUnit.HOURS);
    }

    /**
     * 获取用户mfa绑定状态
     *
     * @param userId userId
     * @return T|F
     */
    public Boolean getMfaBindingStatus(Long userId) {
        String key = String.format(MFA_BINDING_STATUS_KEY, userId);
        String value = stringRedisTemplate.opsForValue().get(key);
        return value != null && userId.equals(Long.valueOf(value));
    }

    /** ----------------------------------------------------------------------------------------- */
}
