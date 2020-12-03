package com.wyc.elegant.admin.controller.auth;

import com.wyc.elegant.admin.common.R;
import com.wyc.elegant.admin.controller.BaseController;
import com.wyc.elegant.admin.model.dto.RegisterDTO;
import com.wyc.elegant.admin.model.entity.TbUser;
import com.wyc.elegant.admin.service.TbUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 用户注册
 *
 * @author Knox 2020/11/12
 */
@Api(tags = "用户注册控制器", value = "RegisterController")
@RestController
@RequestMapping("/auth")
public class RegisterController extends BaseController {

    @Autowired
    private TbUserService userService;

    /**
     * 用户注册
     */
    @PostMapping("/register")
    public R register(@RequestBody @Valid RegisterDTO dto) {
        TbUser tbUser = userService.register(dto);
        if (tbUser == null) {
            return R.error().message("注册失败");
        }
        return R.ok().message("注册成功").data(tbUser);
    }
}
