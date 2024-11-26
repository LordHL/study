package cn.ktl.lab.springmvc.exception;


/**
 * @description 异常code
 *
 */
public interface ErrorCode {

    /**
     * 错误码
     * @return code
     */
    String getCode();

    /**
     * error info
     * @return
     */
    String getMessage();

}
