package cn.ktl.lab.springmvc.external.authservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * @Author lin ho
 * Des: 微软 azure ad sso
 */
@Setter
@Getter
public class SocialLoginResponseResult extends AuthSvcResponseResult <SocialLoginResponseResult.UserData> {
    @JsonAlias("access_token")
    private String accessToken;

    @JsonAlias("refresh_token")
    private String refreshToken;

    @JsonAlias("expires_at")
    private Date expiresAt;

    @JsonAlias("new_user")
    private Boolean newUser;

    @JsonAlias("email_changed")
    private Boolean emailChanged;

    @JsonAlias("user_data")
    private UserData userData;

    @Setter
    @Getter
    public static class UserData {

        @JsonAlias("first_name")
        private String firstName;

        @JsonAlias("last_name")
        private String lastName;

        private String email;

        private String phone;

        @JsonAlias("preferred_language")
        private String preferredLanguage;

        @JsonAlias("job_title")
        private String jobTitle;

        @JsonAlias("office_location")
        private String officeLocation;

        @JsonAlias("business_phones")
        private List<String> businessPhones;

        @JsonAlias("social_provider_id")
        private String socialProviderId;

        @JsonAlias("login_uid")
        private String loginUid;
    }

}
