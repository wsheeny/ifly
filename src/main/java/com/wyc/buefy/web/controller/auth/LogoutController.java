package com.wyc.buefy.web.controller.auth;

import com.wyc.buefy.web.common.R;
import com.wyc.buefy.web.controller.BaseController;
import io.swagger.annotations.Api;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注销
 *
 * @author Knox
 * @date 2020/12/6
 */
@Api(tags = "注销控制器", value = "LogoutController")
@RestController
@RequestMapping("/api/user")
public class LogoutController extends BaseController {

    /**
     * 注销，Token
     */
    @PostMapping("/logout")
    public R logout() {
        SecurityContextHolder.clearContext();
        return R.ok().message("注销成功");
    }
}
