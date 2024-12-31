package cn.ktl.lab.config;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author lin ho
 * Des: TODO
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@Slf4j
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            ClientRegistrationRepository clientRegistrationRepository) throws Exception {
        http.oauth2Login(oauth2 -> oauth2.userInfoEndpoint().oidcUserService(oidcUserService())

        );

        http.logout((logout) -> {
            final var logoutSuccessHandler =
                    new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
            logoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/");
            logout.logoutSuccessHandler(logoutSuccessHandler);
        });

        http.authorizeHttpRequests(requests -> {
            requests.requestMatchers("/", "/favicon.ico").permitAll();
            requests.anyRequest().authenticated(); // 所有请求需认证，但不依赖 Keycloak 权限
        });

        http.oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                        .jwtAuthenticationConverter(jwtAuthenticationConverter()) // 自定义 JWT 转换器
                )
        );
        return http.build();
    }

    /**
     * 自定义 JWT 转换器，从 Access Token 中提取用户信息。
     */
//    @Bean
//    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
//        return (Jwt jwt) -> {
//            // 从 Token 提取信息
//            String username = jwt.getClaimAsString("preferred_username");
//            List<GrantedAuthority> authorities = Collections.emptyList(); // 自定义权限逻辑
//
//            // 返回自定义认证对象
//            return new JwtAuthenticationToken(jwt, authorities, username);
//        };
//    }
    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
        return new Converter<Jwt, AbstractAuthenticationToken>() {
            @Override
            public AbstractAuthenticationToken convert(Jwt jwt) {
                String username = jwt.getClaimAsString("preferred_username");
                List<GrantedAuthority> authorities = Collections.emptyList(); // 自定义权限逻辑
                return new JwtAuthenticationToken(jwt, authorities, username);
            }
        };
    }
    @Bean
    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
        return new OAuth2UserService<OidcUserRequest, OidcUser>() {
            @Override
            public OidcUser loadUser(OidcUserRequest userRequest) {
                OidcIdToken idToken = userRequest.getIdToken();
                OidcUser oidcUser = new DefaultOidcUser(Collections.emptyList(), idToken);

                // 记录登录信息或其他逻辑
                System.out.println("ID Token claims: " + idToken.getClaims());

                return oidcUser;
            }
        };
    }



    /**
     * 自定义 OAuth2 User Service，用于处理登录时的额外逻辑。
     */
//    @Bean
//    public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService() {
//        return (OidcUserRequest userRequest) -> {
//            OidcIdToken idToken = userRequest.getIdToken();
//            OidcUser oidcUser = new DefaultOidcUser(Collections.emptyList(), idToken);
//
//            // 记录登录信息或其他逻辑
//            System.out.println("ID Token claims: " + idToken.getClaims());
//
//            return oidcUser;
//        };
//    }

//    interface AuthoritiesConverter extends Converter<Map<String, Object>, Collection<GrantedAuthority>> {}
//
//    @Bean
//    AuthoritiesConverter realmRolesAuthoritiesConverter() {
//        return claims -> {
//            log.info("Full claims: " + JSONUtil.toJsonPrettyStr(claims));
//            final var realmAccess = Optional.ofNullable((Map<String, Object>) claims.get("realm_access"));
//            final var roles =
//                    realmAccess.flatMap(map -> Optional.ofNullable((List<String>) map.get("roles")));
//            return roles.map(List::stream).orElse(Stream.empty()).map(SimpleGrantedAuthority::new)
//                    .map(GrantedAuthority.class::cast).toList();
//        };
//    }
//
//    @Bean
//    GrantedAuthoritiesMapper authenticationConverter(
//            Converter<Map<String, Object>, Collection<GrantedAuthority>> realmRolesAuthoritiesConverter) {
//        return (authorities) -> authorities.stream()
//                .filter(authority -> authority instanceof OidcUserAuthority)
//                .map(OidcUserAuthority.class::cast).map(OidcUserAuthority::getIdToken)
//                .map(OidcIdToken::getClaims).map(realmRolesAuthoritiesConverter::convert)
//                .flatMap(roles -> roles.stream())
//                .peek(role -> System.out.println("Mapped role: " + role)) // 打印角色
//                .collect(Collectors.toSet());
//    }
//
//    @Bean
//    SecurityFilterChain clientSecurityFilterChain(HttpSecurity http,
//                                                  ClientRegistrationRepository clientRegistrationRepository) throws Exception {
//        http.oauth2Login(Customizer.withDefaults());
//        http.logout((logout) -> {
//            final var logoutSuccessHandler =
//                    new OidcClientInitiatedLogoutSuccessHandler(clientRegistrationRepository);
//            logoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/");
//            logout.logoutSuccessHandler(logoutSuccessHandler);
//        });
//
//        http.authorizeHttpRequests(requests -> {
//            requests.requestMatchers("/", "/favicon.ico").permitAll();
//            requests.requestMatchers("/nice").hasAuthority("NICE");
//            requests.anyRequest().denyAll();
//        });
//
//        return http.build();
//    }
//    private final KeycloakLogoutHandler keycloakLogoutHandler;
//    SecurityConfig(KeycloakLogoutHandler keycloakLogoutHandler) {
//        this.keycloakLogoutHandler = keycloakLogoutHandler;
//    }
//    @Bean
//    protected SessionAuthenticationStrategy sessionAuthenticationStrategy() {
//        return new RegisterSessionAuthenticationStrategy(new SessionRegistryImpl());
//    }
//    @Order(1)
//    @Bean
//    public SecurityFilterChain clientFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .requestMatchers(new AntPathRequestMatcher("/"))
//                .permitAll()
//                .anyRequest()
//                .authenticated();
//        http.oauth2Login()
//                .and()
//                .logout()
//                .addLogoutHandler(keycloakLogoutHandler)
//                .logoutSuccessUrl("/");
//        return http.build();
//    }
//
//    @Order(2)
//    @Bean
//    public SecurityFilterChain resourceServerFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .requestMatchers(new AntPathRequestMatcher("/customers*"))
//                .hasRole("USER")
//                .anyRequest()
//                .authenticated();
//        http.oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()));
//        return http.build();
//    }
//    @Bean
//    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
//        return http.getSharedObject(AuthenticationManagerBuilder.class)
//                .build();
//    }
}
