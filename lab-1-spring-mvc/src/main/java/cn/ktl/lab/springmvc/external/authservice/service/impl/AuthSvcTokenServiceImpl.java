package cn.ktl.lab.springmvc.external.authservice.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.ktl.lab.springmvc.config.AuthSvcConfig;
import cn.ktl.lab.springmvc.exception.BusinessException;
import cn.ktl.lab.springmvc.external.authservice.mapper.AuthSvcTokenClient;
import cn.ktl.lab.springmvc.external.authservice.model.dto.AuthSvcApiKeyDTO;
import cn.ktl.lab.springmvc.external.authservice.service.AuthSvcTokenService;
import cn.ktl.lab.springmvc.service.OAuth2AccessTokenRedisService;

import cn.ktl.lab.springmvc.external.authservice.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthSvcTokenServiceImpl implements AuthSvcTokenService {

    private final AuthSvcConfig authSvcConfig;
    private final AuthSvcTokenClient authSvcTokenClient;
    private final OAuth2AccessTokenRedisService oAuth2AccessTokenRedisService;

    @Override
    public String getAuthServiceApiKey() {
        // 先获取缓存
        String s = oAuth2AccessTokenRedisService.getApiKey();
        if (StrUtil.isNotBlank(s)) {
            return s;
        }
        // 调用接口获取xapikey
        ApiKeyResponseResult apiKeyResponseResult = authSvcTokenClient.generateApiKey(new AuthSvcApiKeyDTO(authSvcConfig.getClientId(), authSvcConfig.getClientSecret()));

        if (apiKeyResponseResult.isSuccess()) {
            String apiKey = apiKeyResponseResult.getApiKey();
            // 固定 7 小时(实际失效时间为8小时)
            oAuth2AccessTokenRedisService.setApiKey(apiKey);
            return apiKey;
        } else {
            throw new BusinessException("没有获取到api-key");
        }
    }
}
