package cn.ktl.lab.config;

/**
 * @Author lin ho
 * Des: TODO
 */

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.core.OAuth2RefreshToken;
import org.springframework.stereotype.Component;

@Component
public class CustomOAuth2AuthorizedClientService implements OAuth2AuthorizedClientService {

    @Override
    public void saveAuthorizedClient(OAuth2AuthorizedClient authorizedClient, org.springframework.security.core.Authentication principal) {
        // 保存授权的客户端信息，例如存储到数据库
        OAuth2RefreshToken refreshToken = authorizedClient.getRefreshToken();
        if (refreshToken != null) {
            System.out.println("Refresh Token: " + refreshToken.getTokenValue());
        }
    }

    @Override
    public void removeAuthorizedClient(String clientRegistrationId, String principalName) {
        // 移除授权的客户端信息
    }

    @Override
    public OAuth2AuthorizedClient loadAuthorizedClient(String clientRegistrationId, String principalName) {
        // 从存储中加载授权的客户端信息
        return null;
    }
}
