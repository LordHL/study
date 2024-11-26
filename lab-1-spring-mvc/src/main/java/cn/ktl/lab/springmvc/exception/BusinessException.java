package cn.ktl.lab.springmvc.exception;



import lombok.Getter;
import lombok.Setter;

/**
 * @description 业务异常
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {


    private String errCode;

    public BusinessException(String msg) {
        super(msg);
    }

    public BusinessException(String errCode, String msg) {
        super(msg);
        this.errCode = errCode;
    }

    public BusinessException(Throwable cause) {
        super(cause);

    }

    public BusinessException(ErrorCode errorCode){
        super(errorCode.getMessage());
        this.errCode = errorCode.getCode();
    }

    public BusinessException(String errCode, String msg, Throwable cause) {
        super(msg, cause);
        this.errCode = errCode;
    }


    public BusinessException(ErrorCode errorCode, Throwable cause){
        super(errorCode.getMessage(),cause);
        this.errCode = errorCode.getCode();
    }

    public static BusinessException of(ErrorCode errorCode){
        return new BusinessException(errorCode);
    }
}
