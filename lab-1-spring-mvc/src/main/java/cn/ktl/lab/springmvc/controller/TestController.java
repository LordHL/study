package cn.ktl.lab.springmvc.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author lin ho
 * Des: TODO
 */
@RestController
@RequestMapping
public class TestController {
    private static final String VERIFY_URL = "https://service.mtcaptcha.com/mtcv1/api/checktoken";
    private static final String SECRET_KEY = "MTPrivat-qTZfsuqlr-FHk4Gsp6HxgJ9CiTkFGD0UkG6jFACv1WlgdZ7JthjIqf6y6dMn";

//    @ApiVersion(CustomVersion.VERSION_1)
//    @PostMapping("/test")
//    public ResponseResult<String> test( ) {
//        String token = "v1(90286cb1,4eacc5e0,MTPublic-DemoKey9M,a2286ed47bd80388c74d0bb179aa7fcb,64WwqwgsjaxMQbXt_5_ykbSwOWL3utCA3dSK5wQhskI4HReJII_Vt0CLidmNvu_WIPfzpAlPQtG2YGF9iq4RrpvPAgLSlvkAFJtPTt_rOsRIvSjDjhv_yDTBNCJN9tqvVpVoy7XwHnGRaMTpVBPZM-0OpEF8KTOdvkWhWg7gNnARfp5dHwCTsfHZI2g5XnR9bTMOnA8Lt0W-KHXK407NtMMfuDj7D9MMaDERBIBeYOEY0PwUBCwazp6ZyIlM7hf2XG3yvng1-Tc_wXoW0QQjW9xR0DF5UHCoCaAQUxwVFWw8MBfkYUbgX6PP_BMssWHTEnd1wjsO18cYDiv42dJz5MYsjwjgxaypWDs2wd99zWrhIvUMDuTewwlmi0_4Oejm0IibnJvQLFrwpt9_YOiSUA**)";
////        String token = "v1(fd8f50fa,1b296df4,MTPublic-DemoKey9M,5d869f181e6811bd38e3fa7d1c1a7f27,cCDbjDFffhoh7_FKZz7O8dYXD7vl4YogQo8VofXUkx623tHIf4nGtUrp1XDsBUbuGYFPH3R63C1GJwSpPztlJXNveOunvCIMQq8Qd60-ItHk06n0ZwDHQDi_ipK5ar5zXW-EF8-gIFfXeLcLDIP11T2kXv2zRqTCINKPR0rlWeOiud3vXuQElj-BPm9r4XkyWRGDgdWgc-m5lNR8zzsJ-PrZguwj-Kh0PjQ82XUDYPRn4YIzOT0EtdR17mCCPHyoWuSaFYzX2fpItSBeCa3mC8EuJL-Q3jrvsiryKBapGJWRRKVYK0z9dxbCBiLQ8U9qRO0UWaMz1JWX7J3iNuy9mK4Kk_5b4S39MUzT-IKDn_lrBnxa-zTGdAxSzp5SA-VATZuhfNgASx1LTc3LK3wlJg**)";
//        Map<String, String> params = new LinkedHashMap<>();
//        params.put("secret", SECRET_KEY);
//        params.put("token", token);
//        Map<String, String> header = new HashMap<>();
//
//
//        String string = OkHttpClientUtil.doPost(VERIFY_URL, params, header);
//
//        // 发送请求到MTCaptcha
//
//
//        System.out.println(string);
//
//        return ResponseResult.success("");
//    }
}
