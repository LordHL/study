package cn.ktl.lab.springmvc.constant;

/**
 * @Author lin ho
 * Des: TODO
 */
public class SecurityConstants {

    /**
     * 登录url
     */
    public static final String AUTH_LOGIN_URL = "/api/common/usermanage/auth/login";


    /**
     * token前缀,用在请求头里Authorization
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 请求头
     */
    public static final String TOKEN_HEADER = "Authorization";

    /**
     * token类型
     */
    public static final String TOKEN_TYPE = "JWT";

    /**
     * 声明用户角色
     */
    public static final String TOKEN_ROLE_CLAIM = "role";

    /**
     * token颁发者身份标识
     */
    public static final String TOKEN_ISSUER = "security";

    /**
     * token覆盖范围
     */
    public static final String TOKEN_AUDIENCE = "security-all";

    /**
     * token有效期9小时
     */
    public static final Long TOKEN_EXPIRATION_TIME = 60 * 60 * 9L;

    /**
     * token有效期为23小时
     */
    public static final Long REFRESH_TOKEN_EXPIRATION_TIME = 60 * 60 * 23L;

    /**
     * 用于判断用户认证过期
     * Provided user's session has expired or has not been authenticated yet
     */
    public final static String USER_SESSION_HAS_EXPIRED = "Provided user's session has expired or has not been authenticated yet";

    /**
     * 二次验证不正确
     */
    public final static String TOTP_ERROR_INVALID = "TOTP invalid";

    /**
     * 微软 add sso
     */
    public final static String AZURE_LOGIN_TYPE = "microsoft";
}
