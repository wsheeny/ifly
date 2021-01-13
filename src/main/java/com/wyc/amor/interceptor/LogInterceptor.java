// package com.wyc.amor.interceptor;
//
// import com.wyc.amor.utils.IpUtil;
// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.stereotype.Component;
// import org.springframework.web.servlet.HandlerInterceptor;
// import org.springframework.web.servlet.ModelAndView;
//
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import java.util.Map;
//
// /**
//  * 接口请求日志
//  *
//  * @author knox
//  */
// @Component
// public class LogInterceptor implements HandlerInterceptor {
//     private final Logger log = LoggerFactory.getLogger(LogInterceptor.class);
//
//     @Override
//     public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
//         long start = System.currentTimeMillis();
//         request.setAttribute("_start", start);
//         return true;
//     }
//
//     @Override
//     public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
//         //
//     }
//
//     @Override
//     public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
//         long start = (long) request.getAttribute("_start");
//         String actionName = request.getRequestURI();
//         String clientIp = IpUtil.getIpAddr(request);
//         StringBuilder logString = new StringBuilder();
//         logString.append(clientIp).append("|").append(actionName).append("|");
//         Map<String, String[]> params = request.getParameterMap();
//         params.forEach((key, value) -> {
//             logString.append(key);
//             logString.append("=");
//             for (String paramString : value) {
//                 logString.append(paramString);
//             }
//             logString.append("|");
//         });
//         long executionTime = System.currentTimeMillis() - start;
//         logString.append("time=").append(executionTime).append("ms");
//         log.info(logString.toString());
//     }
// }
