package cn.ktl.lab.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.oidc.StandardClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author lin ho
 * Des: TODO
 */
@RestController
@Slf4j
public class MeController {
    @GetMapping("/me")
    public String getGretting(JwtAuthenticationToken auth) {
        log.info("getClaimAsString = {}",auth.getToken().getClaimAsString(StandardClaimNames.PREFERRED_USERNAME));
        log.info("getAuthorities = {}",auth.getAuthorities());
        return auth.getToken().getId();
    }

    public static record UserInfoDto(String name, List roles) {}
}
