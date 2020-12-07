package com.wyc.buefy.web.controller.auth;

import com.wyc.buefy.web.common.R;
import com.wyc.buefy.web.controller.BaseController;
import com.wyc.buefy.web.model.dto.LoginDTO;
import com.wyc.buefy.web.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录处理
 *
 * @author Knox
 * @date 2020/12/6
 */
@Api(tags = "登录控制器", value = "LoginController")
@RestController
@RequestMapping("/api/user")
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ApiOperation(value = "登录")
    public R userLogin(@Valid @RequestBody LoginDTO dto) {
        String token = userService.executeLogin(dto);
        if (ObjectUtils.isEmpty(token)) {
            return R.error().code(HttpServletResponse.SC_UNAUTHORIZED).message("用户名或密码错误");
        }
        Map<String, String> map = new HashMap<>(16);
        map.put("token", token);
        return R.ok().data(map).message("登录成功");
    }

}
