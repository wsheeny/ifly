package com.wyc.elegant.admin.controller.admin;

import com.wyc.elegant.admin.controller.BaseController;
import com.wyc.elegant.admin.service.AdminUserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台用户登录
 *
 * @author Yeeep 2020/11/23
 */
@Api(tags = "后台系统-用户处理器")
@RestController
@RequestMapping("/admin/user")
public class AdminUserController extends BaseController {

    @Autowired
    private AdminUserService adminUserService;


    // @ApiOperation(value = "后台系统用户登录", notes = "", httpMethod = "POST")
    // @ApiImplicitParam(value = "登录信息")
    // @PostMapping("/login")
    // public R adminLogin(@Valid @RequestBody LoginDTO dto, BindingResult bindingResult) {
    //     if (bindingResult.hasErrors()) {
    //         return R.error().message(Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage());
    //     }
    //     String username = dto.getUsername();
    //     String password = dto.getPassword();
    //
    //     try {
    //         Subject subject = getSubject();
    //         Boolean rememberMe = dto.getRememberMe();
    //         UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password, rememberMe);
    //         subject.login(usernamePasswordToken);
    //
    //         User user = (User) getSubject().getPrincipal();
    //         // String token = jwtTokenUtil.generateToken(user);
    //         // user.setToken(token);
    //         // userService.updateById(user);
    //         // return R.ok().data(token);
    //     } catch (UnknownAccountException | IncorrectCredentialsException e) {
    //         log.error("账号密码不匹配，请重新登录");
    //     }
    //     return R.error().code(401).message("账号密码不匹配，请校验后再次登录");
    // }
}
