package com.wyc.rhapsody.backend.config.security;

import cn.hutool.json.JSONUtil;
import com.wyc.rhapsody.backend.common.R;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当未登录或者token失效访问接口时，自定义的返回结果
 *
 * @author Knox
 * @date 2020/12/5
 */
@Component
public class MyRestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        R r = new R();
        r.setCode(HttpServletResponse.SC_UNAUTHORIZED).setMessage("未登录，或Token已失效");
        response.getWriter().println(JSONUtil.parse(r));
        response.getWriter().flush();
    }
}
