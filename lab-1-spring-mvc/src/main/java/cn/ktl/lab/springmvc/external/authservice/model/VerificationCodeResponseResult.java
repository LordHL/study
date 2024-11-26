package cn.ktl.lab.springmvc.external.authservice.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author lin ho
 * Des: 发送验证码结果
 */

@Setter
@Getter
public class VerificationCodeResponseResult extends AuthSvcResponseResult {

    /**
     * 尝试次数
     */
    @JsonAlias("remaining_attempts")
    private Integer remainingAttempts;

}
