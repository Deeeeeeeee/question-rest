package com.seal_de.common.interceptor;

import com.seal_de.common.security.TokenManager;
import com.seal_de.common.util.VerifyUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by seal_de on 2017/4/14.
 */
public class TokenInterceptor implements HandlerInterceptor {
    private TokenManager tokenManager;

    public TokenInterceptor(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestMethod = httpServletRequest.getMethod();
        if("OPTIONS".equals(requestMethod))
            return true;

        String token = httpServletRequest.getHeader("authorization");
        token = token.substring(1, token.length()-1);

        VerifyUtil.isTrue(tokenManager.checkToken(token),
                HttpStatus.UNAUTHORIZED, "token验证失败");
        httpServletRequest.setAttribute("token_username", tokenManager.getUsername(token));
//        httpServletRequest.setAttribute("token_username", "jm1");
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
