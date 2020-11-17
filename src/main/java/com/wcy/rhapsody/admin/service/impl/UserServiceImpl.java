package com.wcy.rhapsody.admin.service.impl;

import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.UserMapper;
import com.wcy.rhapsody.admin.modules.dto.UserRegisterDTO;
import com.wcy.rhapsody.admin.modules.entity.User;
import com.wcy.rhapsody.admin.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户 实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Value("${user.avatar}")
    private String avatar;

    @Value("${user.signature}")
    private String signature;

    @Override
    public User selectByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public int createUser(UserRegisterDTO dto) {
        User user = new User();
        user.setUsername(dto.getName())
                .setAlias(dto.getName())
                .setPassword(BCrypt.hashpw(dto.getPass(), BCrypt.gensalt()))
                .setEmail(dto.getEmail())
                .setAvatar(avatar)
                .setActive(false)
                .setBio("")
                .setSignature(signature);
        int insert = this.baseMapper.insert(user);

        String activeUrl = URLUtil.normalize("localhost:9999/user/active");

        // 发送激活邮件
        String content = "请在30分钟内激活您的账号，如非本人操作，请忽略 </br > " +
                "<a href=\"" + activeUrl + "\" target =\"_blank\" '>点击激活账号</a>";


        MailUtil.send(dto.getEmail(), "R社区账号激活", content, true);


        return insert;
    }
}
