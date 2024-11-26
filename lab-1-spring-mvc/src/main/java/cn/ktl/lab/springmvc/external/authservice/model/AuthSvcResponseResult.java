package cn.ktl.lab.springmvc.external.authservice.model;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class AuthSvcResponseResult<T> {

    private String message;

    @JsonAlias("trace_id")
    private String traceId;


    private T payload;

    private Object error;


    public boolean isSuccess() {
        return ObjectUtil.isNull(error);
    }
}
