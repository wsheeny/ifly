package com.wyc.buefy.web.config.security;

import cn.hutool.json.JSONUtil;
import com.wyc.buefy.web.common.R;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 当访问接口没有权限时，自定义的返回结果
 *
 * @author Knox
 * @date 2020/12/5
 */
@Component
public class MyRestfulAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        R r = new R();
        r.setCode(HttpServletResponse.SC_FORBIDDEN).setMessage("权限不足");
        response.getWriter().println(JSONUtil.parse(r));
        response.getWriter().flush();
    }
}
