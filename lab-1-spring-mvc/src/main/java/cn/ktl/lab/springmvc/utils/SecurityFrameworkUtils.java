package cn.ktl.lab.springmvc.utils;

import cn.hutool.extra.servlet.JakartaServletUtil;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.lang.Nullable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;

/**
 * @Author lin ho
 * Des: TODO
 */
public class SecurityFrameworkUtils {

    private static final String REQUEST_ATTRIBUTE_LOGIN_USER_ID = "login_user_no";

    /**
     * 获得当前认证信息
     *
     * @return 认证信息
     */
    public static Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            return null;
        }
        return context.getAuthentication();
    }

//    /**
//     * 设置当前用户
//     * @param loginUserVO 登录用户
//     * @param request 请求
//     */
//    public static void setLoginUser(LoginUserVO loginUserVO, HttpServletRequest request){
//        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
//                loginUserVO, null, Collections.emptyList());
//        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//
//        setLoginUserNo(request, loginUserVO.getUserNo());
//    }

    public static void setLoginUserNo(ServletRequest request, String userNo) {
        request.setAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_ID, userNo);
    }


    /**
     * 获得当前用户的编号，从请求中
     * 注意：该方法仅限于 framework 框架使用！！！
     *
     * @param request 请求
     * @return 用户编号
     */
    public static String getLoginUserNo(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        return (String) request.getAttribute(REQUEST_ATTRIBUTE_LOGIN_USER_ID);
    }

    /**
     * 获取当前用户
     *
     * @return 当前用户
     */
//    @Nullable
//    public static LoginUserVO getLoginUser() {
//        Authentication authentication = getAuthentication();
//        if (authentication == null) {
//            return null;
//        }
//        return authentication.getPrincipal() instanceof LoginUserVO ? (LoginUserVO) authentication.getPrincipal() : null;
//    }

    /**
     * 获得当前用户的编号，从上下文中
     *
     * @return 用户id
     */
//    @Nullable
//    public static Long getLoginId() {
//        LoginUserVO loginUser = getLoginUser();
//        return loginUser != null ? loginUser.getId() : null;
//    }
//
//    /**
//     * 查询获取当前登录的用户， 如果为空， 返回none
//     *
//     * @return
//     */
//    @Nullable
//    public static String getLoginIdIfNullDefault() {
//        LoginUserVO loginUser = getLoginUser();
//        return loginUser != null ? String.valueOf(loginUser.getId()) : "none";
//    }


    /**
     * 获得当前用户的编号，从上下文中
     *
     * @return 用户id
     */
//    @Nullable
//    public static String getLoginUserNo() {
//        LoginUserVO loginUser = getLoginUser();
//        return loginUser != null ? loginUser.getUserNo() : null;
//    }


    /**
     * 直接通过response返回 数据
     * @param response HttpServletResponse
     * @param object object
     */
    public static void writeJSON(HttpServletResponse response, Object object) {
        String content = JsonUtils.toJsonString(object);
        JakartaServletUtil.write(response, content, MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

    /**
     * @param request 请求
     * @return ua
     */
    public static String getUserAgent(HttpServletRequest request) {
        String ua = request.getHeader("User-Agent");
        return ua != null ? ua : "";
    }

    /**
     *  获取浏览器
     * @return 浏览器
     */
    public static String getUserAgent() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return getUserAgent(request);
    }

    /**
     * 获得请求
     *
     * @return HttpServletRequest
     */
    public static HttpServletRequest getRequest() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (!(requestAttributes instanceof ServletRequestAttributes)) {
            return null;
        }
        return ((ServletRequestAttributes) requestAttributes).getRequest();
    }

    /**
     * 获取客户端ip
     *
     * @return client ip
     */
    public static String getClientIP() {
        HttpServletRequest request = getRequest();
        if (request == null) {
            return null;
        }
        return JakartaServletUtil.getClientIP(request);
    }
    /**
     * 获取客户端ip
     *
     * @return client ip
     */
    public static String getClientIP(HttpServletRequest request) {
        return JakartaServletUtil.getClientIP(request);
    }
}
