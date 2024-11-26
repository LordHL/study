package cn.ktl.lab.springmvc.exception;


import lombok.Getter;


/**
 * 注意: 定义错误码时, 先看下 ResultCode 是否已经定义
 *
 * @see
 */
@Getter
public enum UmErrorCodeEnum implements ErrorCode {
    // 系统相关的

    /**
     * 用户不存在!
     */
    UM_USER_IS_NOT_EXISTS("20001", "user does not exist!"),
    /**
     * 账号或密码不正确!
     */
    UM_USER_USERNAME_OR_PASSWORD_ERROR("20002", " account or password is incorrect!"),
    /**
     * 系统默认用户不可删除!
     */
    UM_USER_CANNOT_DELETE("20003", " default user cannot be deleted!"),

    /**
     * ROLE_CANNOT_DELETE
     */
    UM_ROLE_CANNOT_DELETE("20004", " default role cannot be deleted!"),

    /**
     * 该用户组名称已存在!
     */
    UM_GROUP_NAME_HAS_EXIST("20005", "the user group name already exists!"),

    /**
     * 外部群组不允许修改或者添加
     */
    UM_EXTERNAL_GROUP_EDIT_ADD_FORBIDDEN("20006", "the external group cannot add or edit!"),


    /**
     * 编辑群组, groupId 不能为空!
     */
    UM_GROUP_ID_IS_NULL("20007", "the group id cannot be null when edit!"),

    /**
     * 群组不存在
     */
    UM_GROUP_IS_NOT_EXISTS("20008", "the group does not exist!!"),

    /**
     * 默认群组不允许修改或者添加
     */
    UM_DEFAULT_GROUP_EDIT_ADD_FORBIDDEN("20009", "the default group cannot edit!"),

    /**
     * 群组类型不允许修改
     */
    UM_GROUP_TYPE_EDIT_FORBIDDEN("20010", "the group type cannot be modified!"),

    /**
     * 当前dict没有找到
     */
    UM_DICT_NOT_EXIST("20011", "dict not exist!"),

    /**
     * dict字段 code不允许修改
     */
    UM_DICT_FIELD_CODE_EDIT_FORBIDDEN("20012", "dict field code can't be modify"),

    /**
     * dict字段 parentCode
     */
    UM_DICT_FIELD_PARENT_CODE_EDIT_FORBIDDEN("20013", "dict field parentCode can't be modify"),

    /**
     * 用户账号未激活
     */
    UM_USER_STATUS_IS_INACTIVE("20014", "current user is inactive"),

    /**
     *修改的密码new pwd 和 confirm pwd不一致
     */
    UM_CHANGE_PASSWORD_INCONSISTENT("20015","the newly modified password does not match the confirmed password!"),

    /**
     * 密码格式不正确
     * Password Length:
     * Minimum: 8 characters
     * Maximum: 64 characters
     * Complexity Requirements:
     * The password should contain characters from three of the following categories:
     *       - At least 1 Uppercase letter(A through Z).
     *       - At least 1 Lowercase letter (a through z).
     *       - At least 1 Base 10 digit (0 through 9).
     * - At least 1 Special character: '-!"#$%&()*,./:;?@[]^_`{|}~+<=>
     * Unicode character is not allowed.
     */
    UM_PASSWORD_FORMAT_INCORRECT("20016","the password format is incorrect!"),

    /**
     * refreshToken 无效
     */
    UM_REFRESH_TOKEN_INVALID("20017", "refresh token invalid!"),


    // 21 auth service 相关的
    /**
     * 用户名已存在
     */
    UM_USERNAME_HAS_EXIST("21001", "the username already exists!"),

    /**
     * email 已存在
     */
    UM_USER_EMAIL_HAS_EXIST("21002", "the user email already exists!"),

    /**
     * 登录Auth service 失败
     */
    UM_AUTH_SERVICE_LOGIN_FAILED("21003", "login auth service failed!"),

    /**
     * 验证码已过期
     */
    UM_VERIFICATION_CODE_HAS_EXPIRED("21004", "the verification code has expired!"),

    /**
     * 登录的邮箱不能为空
     */
    UM_LOGIN_EMAIL_NOT_EMPTY("21005", "the login mailbox cannot be empty!"),

    /**
     * 登录的密码不能为空
     */
    UM_LOGIN_PWD_NOT_EMPTY("21006", "the login password cannot be empty!"),

    /**
     * totp code 不能为空
     */
    UM_LOGIN_TOTP_NOT_EMPTY("21007", "the login totp code cannot be empty!"),

    /**
     * 登录的用户名（email）不存在
     */
    UM_LOGIN_EMAIL_NOT_EXIST("21008", "the login email not exist!"),


    /**
     * totp 验证失败
     */
    UM_TOTP_ERROR_INVALID("21009", "totp invalid!"),
    /**
     * 用户认证过期
     */
    UM_USER_SESSION_EXPIRED("21010", "provided user's session has expired!"),
    /**
     * 调用auth get public key 失败
     */
    UM_INVOKE_AUTH_GET_PUBLIC_KEY_FAILED("21011", "failed to invoke auth get public key!"),

    /**
     * auth service 调用返回错误
     */
    UM_USER_AUTH_SVC_CALL_FAILED("21012", "Auth service call failed!"),

    /**
     * xApiKey生成失败
     */
    UM_USER_AUTH_SVC_X_API_KEY_FAILED("21013", "Auth service api key generation failed!"),


    /**
     * 调用auth recovery key 失败
     */
    UM_INVOKE_AUTH_RECOVERY_KEY_FAILED("21014", "failed to invoke auth recovery key!"),


    /**
     *调用auth 刷新accessToken 失败
     */
    UM_USER_CALL_AUTH_REFRESH_ACCESS_TOKEN_FAILED("21015", "invoke Auth service access-token-refresh api failed!"),

    /**
     * 调用auth修改密码失败
     */
    UM_USER_AUTH_CHANGE_PASSWORD_FAILED("21016", "invoke Auth service change-user-password api failed!"),

    /**
     * 调用auth social login 失败
     */
    UM_USER_INVOKE_AUTH_SOCIAL_LOGIN_FAILED("21017", "invoke Auth service social-login api failed!"),

    /**
     * 调用auth update user 失败
     */
    UM_USER_INVOKE_AUTH_UPDATE_USER_FAILED("21018", "invoke Auth service update-user api failed!"),


    /*********************  system role   *******************************/
    UM_SYSTEM_ROLE_NOT_EXIST("22001", "system role not exist!"),

    UM_SYSTEM_ROLE_NAME_EXIST("22002", "system role name exist!"),


    /*********************  business role   *******************************/
    UM_BUSINESS_ROLE_NOT_EXIST("23001", "business role not exist!"),
    UM_BUSINESS_ROLE_NAME_EXIST("23002", "business role name exist!"),
    UM_BUSINESS_ROLE_SCOPE_MODIFY_FORBIDDEN("23003", "business role scope cannot be modified!"),


    /*********************  Third platform   *******************************/
    UM_THIRD_PLATFORM_NOT_EXIST("24001", "third platform not exist!"),


    /*********************  FraudDetection  *******************************/
    UM_VPN_FRAUD_NOT_ALLOW_LOGIN("25001", "VPN users are not allowed to log in!"),

    /*********************  microsoft sso  *******************************/
    UM_MICROSOFT_EMAIL_IS_EMPTY("26001", "there is currently no such person in the company!"),


    ;

    final String code;
    final String message;

    UmErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
