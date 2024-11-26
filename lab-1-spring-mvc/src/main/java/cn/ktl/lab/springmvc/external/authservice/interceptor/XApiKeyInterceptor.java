package cn.ktl.lab.springmvc.external.authservice.interceptor;

import cn.ktl.lab.springmvc.external.authservice.service.AuthSvcTokenService;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
@Scope("prototype")
@RequiredArgsConstructor
public class XApiKeyInterceptor extends BasePathMatchInterceptor {

    private final AuthSvcTokenService authSvcTokenService;

    @Override
    protected Response doIntercept(Chain chain) throws IOException {
        Request request = chain.request();
        String authServiceApiKey = authSvcTokenService.getAuthServiceApiKey();
        Request rq = request.newBuilder().header("x-api-key", authServiceApiKey).build();
        return chain.proceed(rq);
    }
}
