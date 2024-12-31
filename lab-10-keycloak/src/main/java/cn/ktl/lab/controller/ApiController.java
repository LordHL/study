package cn.ktl.lab.controller;

import jakarta.annotation.Resource;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lin ho
 * Des: TODO
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    @Resource
    private RestTemplate restTemplate;
    @GetMapping("/user")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        if (authentication instanceof JwtAuthenticationToken jwtAuth) {
            Jwt jwt = jwtAuth.getToken();
            String username = jwt.getClaim("preferred_username");
            String email = jwt.getClaim("email");

            // 返回用户信息
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("username", username);
            userInfo.put("email", email);
            return ResponseEntity.ok(userInfo);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    public String refreshToken(String refreshToken) {


        // 请求体
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", "your-client-id");
        formData.add("client_secret", "your-client-secret");
        formData.add("grant_type", "refresh_token");
        formData.add("refresh_token", refreshToken);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, headers);

        // 调用 Keycloak 的 Token 刷新接口
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "https://your-keycloak-domain/realms/your-realm/protocol/openid-connect/token",
                request,
                Map.class
        );

        return response.getBody().get("access_token").toString();
    }

}

