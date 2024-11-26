package cn.ktl.lab.springmvc.base;


import cn.ktl.lab.springmvc.exception.ErrorCode;
import lombok.Getter;

/**
 * 通用业务异常状态码
 */

@Getter
public enum ResultCode implements ErrorCode {

    /**
     * 系统级别 网络/sql      模块错误码范围：10001-19999
     * UserManagement       模块错误码范围：20001-29999
     * ProjectManagement    模块错误码范围：30001-39999
     * Recruitment          模块错误码范围：40001-49999
     * Payment              模块错误码范围：50001-59999
     * Notification         模块错误码范围：60001-69999
     * Message              模块错误码范围：70001-79999
     * Message              模块错误码范围：70001-79999
     * Crowd                模块错误码范围：80001-89999
     * Fraudulent           模块错误码范围：90001-99999
     */

    /****************** 系统异常码 ********************************/
    SUCCESS("200", "success"),
    //无效的请求或请求参数错误
    BAD_REQUEST("400","parameter error"),
    //未授权，账号未登录
    UNAUTHORIZED("401","unauthorized account not logged in"),
    //没有该操作权限
    FORBIDDEN("403","operation permission is not granted"),
    //系统异常
    INTERNAL_SERVER_ERROR("500","system error"),

    FAIL("-1","fail"),


    /****************** 系统级别异常码********************************/
    NET_ERROR("10001", "network error"),
    HTTP_REQUEST_ERROR("10002", "Http request error"),

    /**
     * 公钥解析没有此算法
     */
    PUBLIC_KEY_PARSING_NO_SUCH_ALGORITHM("10003", "Public key parsing does not have this algorithm"),
    /**
     *
     */
    INVALID_PUBLIC_KEY("10004", "Invalid public key"),

    /**
     * token 无效
     */
    INVALID_ACCESS_TOKEN("10005","invalid token"),

    ;



    private String code;
    private String message;

    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public static String getMessageByName(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static String getCodeByName(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }


}
