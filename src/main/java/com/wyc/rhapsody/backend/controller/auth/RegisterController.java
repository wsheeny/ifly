package com.wyc.rhapsody.backend.controller.auth;

import com.wyc.rhapsody.backend.common.R;
import com.wyc.rhapsody.backend.controller.BaseController;
import com.wyc.rhapsody.backend.model.dto.RegisterDTO;
import com.wyc.rhapsody.backend.model.entity.TbUser;
import com.wyc.rhapsody.backend.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * 账号注册
 *
 * @author Knox
 * @date 2020/12/6
 */
@Api(tags = "注册控制器", value = "RegisterController")
@RestController
@RequestMapping("/api/user")
public class RegisterController extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    @ApiOperation(value = "注册")
    public R register(@Valid @RequestBody RegisterDTO dto) {
        TbUser user = userService.executeRegister(dto);
        if (ObjectUtils.isEmpty(user)) {
            return R.error().message("账号注册失败");
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("user", user);
        return R.ok().data(map).message("账号注册成功");
    }

}
