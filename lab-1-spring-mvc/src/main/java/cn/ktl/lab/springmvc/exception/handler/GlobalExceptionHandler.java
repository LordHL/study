package cn.ktl.lab.springmvc.exception.handler;



import cn.ktl.lab.springmvc.base.ResponseResult;
import cn.ktl.lab.springmvc.base.ResultCode;
import cn.ktl.lab.springmvc.exception.BusinessException;
import cn.ktl.lab.springmvc.exception.OAuthResponseException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


/**
 * @description 全局异常处理器
 * @date 2020-02-23
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = Exception.class)
    public ResponseResult<?> handleException(Exception e) {
        // 打印堆栈信息
        log.error("[Exception] - 未知异常引发的异常的堆栈信息:", e);
        return ResponseResult.failure(ResultCode.INTERNAL_SERVER_ERROR);
    }



    /**
     * 处理OAuth2 授权异常
     */
    @ExceptionHandler(value = OAuthResponseException.class)
    public ResponseResult<?> oAuth2ResponseException(OAuthResponseException e) {
        // 打印堆栈信息
        log.error("[OAuthResponseException] - OAuth2 授权异常引发的异常的堆栈信息:", e);
        return ResponseResult.failure(e.getErrCode(), e.getMessage());
    }


    /**
     * 处理自定义异常
     */
    @ExceptionHandler(value = BusinessException.class)
    public ResponseResult<?> badRequestException(BusinessException e) {
        // 打印堆栈信息
        log.error("[BusinessException] - 引发的异常的堆栈信息:", e);
        return ResponseResult.failure(e.getErrCode(), e.getMessage());
    }

    /**
     *
     * 处理请求参数类型错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseResult<?> methodArgumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException e) {
        log.error("[missingServletRequestParameterExceptionHandler] - 引起异常的堆栈信息:", e);
        String msg = String.format(ResultCode.BAD_REQUEST.getMessage() + ":%s", e.getMessage());
        return ResponseResult.failure(ResultCode.BAD_REQUEST.getCode(), msg);
    }

    /**
     * 处理所有接口数据验证异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseResult<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 打印堆栈信息
        log.error("[MethodArgumentNotValidException] - 引起异常的堆栈信息:", e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        assert fieldError != null;
        String msg = String.format(ResultCode.BAD_REQUEST.getMessage() + ":%s", fieldError.getDefaultMessage());
        return ResponseResult.failure(ResultCode.BAD_REQUEST, msg);
    }

    @ExceptionHandler(BindException.class)
    public ResponseResult<?> bindExceptionHandler(BindException ex) {
        // 打印堆栈信息
        log.error("[handleBindException] - 引起异常的堆栈信息:", ex);
        FieldError fieldError = ex.getFieldError();
        assert fieldError != null;
        String msg = String.format(ResultCode.BAD_REQUEST.getMessage() + ":%s", fieldError.getDefaultMessage());
        return ResponseResult.failure(ResultCode.BAD_REQUEST.getCode(), msg);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseResult<?> constraintViolationExceptionHandler(ConstraintViolationException ex) {
        log.warn("[constraintViolationExceptionHandler]", ex);
        ConstraintViolation<?> constraintViolation = ex.getConstraintViolations().iterator().next();
        String msg = String.format(ResultCode.BAD_REQUEST.getMessage() + ":%s", constraintViolation.getMessage());
        return ResponseResult.failure(ResultCode.BAD_REQUEST.getCode(), msg);
    }
}
