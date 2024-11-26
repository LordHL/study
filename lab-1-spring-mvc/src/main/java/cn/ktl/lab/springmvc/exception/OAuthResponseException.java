package cn.ktl.lab.springmvc.exception;


import lombok.Getter;
import lombok.Setter;

/**
 * @description OAuth2 授权自定义异常
 */

@Getter
@Setter
public class OAuthResponseException extends RuntimeException {

    private String errCode;

    public OAuthResponseException(String msg) {
        super(msg);
    }

    public OAuthResponseException(String errCode,String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public OAuthResponseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errCode = errorCode.getCode();
    }



    public static OAuthResponseException of(ErrorCode errorCode){
        return new OAuthResponseException(errorCode);
    }
}
