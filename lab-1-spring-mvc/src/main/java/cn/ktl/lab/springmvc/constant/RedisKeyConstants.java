package cn.ktl.lab.springmvc.constant;


/**
 *  Redis Key 枚举类
 *
 */
public interface RedisKeyConstants {


    /**
     * 角色的缓存
     * <p>
     * KEY 格式：role:{id}
     * VALUE 数据类型：String 角色信息
     */
    String ROLE = "role";

    /**
     * 用户拥有的角色编号的缓存
     * <p>
     * KEY 格式：user_role_ids:{userId}
     * VALUE 数据类型：String 角色编号集合
     */
    String USER_ROLE_ID_LIST = "user_role_ids";

    /**
     * 拥有指定菜单权限的角色编号的缓存
     * <p>
     * KEY 格式：user_role_ids:{permissionId}
     * VALUE 数据类型：String 角色编号集合
     */
    String PERMISSION_ROLE_ID_LIST = "permission_role_ids";

    /**
     * 拥有权限对应的菜单权限编号数组的缓存
     * <p>
     * KEY 格式：permission_menu_ids:{permission}
     * VALUE 数据类型：String 菜单编号数组
     */
    String PERMISSION_MENU_ID_LIST = "permission_menu_ids";

    /**
     * OAuth2 客户端的缓存
     * <p>
     * KEY 格式：oauth_client:{id}
     * VALUE 数据类型：String 客户端信息
     */
    String OAUTH_CLIENT = "oauth_client";

    /**
     * 访问令牌的缓存
     * <p>
     * KEY 格式：oauth2_access_token:{token}
     * <p>
     * 由于动态过期时间，使用 RedisTemplate 操作
     */
    String OAUTH2_ACCESS_TOKEN = "oauth2_access_token:%s";
    /**
     * 访问令牌的缓存
     * <p>
     * KEY 格式：OAUTH2_TOKENS:{userId}
     * <p>
     * 由于动态过期时间，使用 RedisTemplate 操作
     */
    String OAUTH2_TOKENS = "oauth2_tokens:%s";

    /**
     * refresh 缓存
     * <p>
     * KEY 格式：oauth2_refresh_token:{refreshToken}
     * <p>
     * 由于动态过期时间，使用 RedisTemplate 操作
     */
    String OAUTH2_REFRESH_TOKEN = "oauth2_refresh_token:%s";

    /**
     * refresh 缓存
     * <p>
     * KEY 格式：OAUTH2_REFRESH_TOKENS:{userId}
     * <p>
     * 由于动态过期时间，使用 RedisTemplate 操作
     */
    String OAUTH2_REFRESH_TOKENS = "oauth2_refresh_tokens:%s";

    /**
     * Verify the public key of the token
     * key verify_token_public_key
     * value public key
     */
    String VERIFY_TOKEN_PUBLIC_KEY = "verify_token_public_key";


    /**
     * 存储authSvc 的apiKey
     */
    String AUTH_SERVICE_API_KEY = "userManagement:authService:xApiKey";

    /**
     * 用户注册暂存用户信息
     * 格式：user_register_info:{email}
     */
    String USER_REGISTER_KEY = "user_register_info:%s";

    /**
     * 用户的用户组缓存
     * 格式：user_groups:{userId}
     */
    String USER_GROUP_KEY = "user_groups:%s";

    /**
     * 用户组-systemRole 缓存
     * 格式：group_roles:{groupId}
     */
    String GROUP_ROLE_PREFIX = "group_roles";
    /**
     * 用户组-systemRole 缓存
     * 格式：group_roles:{groupId}
     */
    String GROUP_ROLE_KEY = "group_roles:%s";
    /**
     * systemRole-权限 缓存
     * 格式：role_permissions:{roleId}
     */
    String ROLE_PERMISSION_PREFIX = "role_permissions";
    /**
     * systemRole-权限 缓存
     * 格式：role_permissions:{roleId}
     */
    String ROLE_PERMISSION_KEY = "role_permissions:%s";

    /**
     * 用户 - 业务角色 缓存
     * 格式：user_business_roles:{userId}
     */
    String USER_BUSINESS_ROLE_PREFIX = "user_business_roles";

    /**
     * 用户 - 业务角色 缓存
     * 格式：user_business_roles:{userId}
     */
    String USER_BUSINESS_ROLE_KEY = "user_business_roles:%s";

    /**
     * 业务角色 - 权限 缓存
     * 格式：business_role_permissions:{businessRoleId}
     */
    String BUSINESS_ROLE_PERMISSION_PREFIX = "business_role_permissions";
    /**
     * 业务角色 - 权限 缓存
     * 格式：business_role_permissions:{businessRoleId}
     */
    String BUSINESS_ROLE_PERMISSION_KEY = "business_role_permissions:%s";

    /**
     * MFA 绑定的状态key
     */
    String MFA_BINDING_STATUS_KEY = "mfa_binding_status:%s";


    /**
     * 存储dict 带有子及后台所有的数据
     */
    String DICT_DESCENDANTS_KEY = "userManagement:dict";

}
