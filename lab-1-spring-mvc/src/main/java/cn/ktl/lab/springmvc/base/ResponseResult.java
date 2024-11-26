package cn.ktl.lab.springmvc.base;

import cn.ktl.lab.springmvc.exception.ErrorCode;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = -2557292092223315527L;

    private boolean success;
    private String code;
    private String message;
    private long total;
    private T data;


    public static <T> ResponseResult<T> success(T data) {
        return success(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, 0);
    }


    public static <T> ResponseResult<T> success(T data, long total) {
        return success(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data, total);
    }


    public static <T> ResponseResult<T> success(String code, String message, T data) {
        return success(code, message, data, 0);
    }

    public static <T> ResponseResult<T> success(ResultCode resultCode, T data) {
        return new ResponseResult<T>(true, resultCode.getCode(), resultCode.getMessage(),0, data);
    }


    public static <T> ResponseResult<T> success(String code, String message, T data, long total) {
        return new ResponseResult<>(true, code, message, total, data);
    }


    public static <T> ResponseResult<T> failure(ErrorCode errorCode) {
        return failure(errorCode, null);
    }


    public static <T> ResponseResult<T> failure(ErrorCode errorCode, T data) {
        return new ResponseResult<>(false, errorCode.getCode(), errorCode.getMessage(), 0, data);
    }


    public static <T> ResponseResult<T> failure(String code, String message) {
        return failure(code, message, null);
    }


    public static <T> ResponseResult<T> failure(String code, String message, T data) {
        return new ResponseResult<>(false, code, message, 0, data);
    }

    // 仅带有消息的失败响应（默认为内部服务器错误）
    public static <T> ResponseResult<T> failure(String message) {
        return failure(ResultCode.INTERNAL_SERVER_ERROR.getCode(), message, null);
    }

    @JsonIgnore
    private void setResultCode(ErrorCode code) {
        this.code = code.getCode();
        this.message = code.getMessage();
    }
}
