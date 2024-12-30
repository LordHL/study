package cn.ktl.lab.controller;

import ch.qos.logback.core.model.Model;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Objects;

/**
 * @Author lin ho
 * Des: TODO
 */
public class KeycloakController {

    @GetMapping("/")
    public String getIndex(org.springframework.ui.Model model, Authentication auth) {
        model.addAttribute(
                "name",
                auth instanceof OAuth2AuthenticationToken oauth && oauth.getPrincipal() instanceof OidcUser oidc ?
                        oidc.getPreferredUsername() :
                        "");
        model.addAttribute("isAuthenticated", auth != null && auth.isAuthenticated());
        model.addAttribute("isNice", auth != null && auth.getAuthorities().stream().anyMatch(authority -> Objects.equals("NICE", authority.getAuthority())));
        return "index.html";
    }

}
