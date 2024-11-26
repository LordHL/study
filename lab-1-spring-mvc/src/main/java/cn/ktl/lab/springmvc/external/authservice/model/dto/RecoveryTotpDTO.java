package cn.ktl.lab.springmvc.external.authservice.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author lin ho
 * Des:{
 *   "email": "user@example.com",
 *   "recovery_key": "3fa85f64-5717-4562-b3fc-2c963f66afa6"
 * }
 */
@Setter
@Getter
public class RecoveryTotpDTO extends GenerateValidationOtpDTO {

    @JsonProperty("recovery_key")
    private String recoveryKey;
}
