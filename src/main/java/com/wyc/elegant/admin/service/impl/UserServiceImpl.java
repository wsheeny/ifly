package com.wyc.elegant.admin.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.elegant.admin.config.redis.RedisService;
import com.wyc.elegant.admin.mapper.*;
import com.wyc.elegant.admin.model.dto.LoginDTO;
import com.wyc.elegant.admin.model.dto.RegisterDTO;
import com.wyc.elegant.admin.model.entity.*;
import com.wyc.elegant.admin.model.vo.ProfileVO;
import com.wyc.elegant.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 用户 实现类
 *
 * @author Knox 2020/11/7
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, TbUser> implements UserService {

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private TopicMapper topicMapper;

    @Autowired
    private RedisService redisService;

    @Resource
    private ColumnMapper columnMapper;

    @Value("${config.domain}")
    private String domain;

    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public TbUser register(RegisterDTO dto) {
        //查询是否有相同用户名的用户
        TbUser one = this.baseMapper.selectOne((new LambdaQueryWrapper<TbUser>().eq(TbUser::getUsername, dto.getName())));
        Assert.isNull(one, "账号已存在，请更换");

        TbUser one1 = this.baseMapper.selectOne(new LambdaQueryWrapper<TbUser>().eq(TbUser::getEmail, dto.getEmail()));
        Assert.isNull(one1, "邮箱已注册，请直接登录");

        TbUser addUser = TbUser.builder()
                .username(dto.getName())
                .alias(dto.getName())
                .password(BCrypt.hashpw(dto.getPass(), BCrypt.gensalt()))
                .email(dto.getEmail())
                .createTime(new Date())
                .status(true)
                .build();

        baseMapper.insert(addUser);

        // 30分钟
        String activeCode = RandomUtil.randomString(8);
        redisService.set("activeCode[" + dto.getName() + "]", activeCode, 30 * 60);
        // 发送邮件
        String activeUrl = URLUtil.normalize(domain + "?user=" + dto.getName() + "&code=" + activeCode);
        String content = "请在30分钟内激活您的账号，如非本人操作，请忽略 </br > " +
                "<a href=\"" + activeUrl + "\" target =\"_blank\" '>点击激活账号</a>";
        MailUtil.send(dto.getEmail(), "【滚雪球】账号激活", content, true);

        return addUser;
    }

    @Override
    public String login(LoginDTO dto) {
        String token = null;
        // try {
        //     UserDetails userDetails = myUserDetailsService.loadUserByUsername(dto.getUsername());
        //     if (!passwordEncoder.matches(dto.getPassword(), userDetails.getPassword())) {
        //         throw new BadCredentialsException("密码不正确");
        //     }
        //     UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
        //             userDetails, null, userDetails.getAuthorities());
        //     SecurityContextHolder.getContext().setAuthentication(authentication);
        //
        //     token = jwtTokenUtil.generateToken(userDetails);
        // } catch (AuthenticationException e) {
        //     log.warn("登录异常:{}", e.getMessage());
        // }
        return token;
    }

    @Override
    public TbUser getUserByUsername(String username) {
        return this.baseMapper.selectOne(new LambdaQueryWrapper<TbUser>().eq(TbUser::getUsername, username));
    }

    @Override
    public List<TbPermission> getPermissionList(String userId) {
        return permissionMapper.selectList(userId);
    }

    @Override
    public ProfileVO getUserProfile(String id) {
        ProfileVO profile = new ProfileVO();
        TbUser user = this.baseMapper.selectById(id);
        BeanUtils.copyProperties(user, profile);
        // 用户文章数
        int count = topicMapper.selectCount(new LambdaQueryWrapper<TbTopic>().eq(TbTopic::getUserId, id));
        profile.setTopicCount(count);

        // 粉丝数
        int followers = followMapper.selectCount((new LambdaQueryWrapper<TbFollow>().eq(TbFollow::getParentId, id)));
        profile.setFollowerCount(followers);
        // 关注数
        int follows = followMapper.selectCount((new LambdaQueryWrapper<TbFollow>().eq(TbFollow::getFollowerId, id)));
        profile.setFollowCount(follows);
        // 专栏数
        Integer integer = columnMapper.selectCount(new LambdaQueryWrapper<TbColumn>().eq(TbColumn::getUserId, id));
        profile.setColumns(integer);

        return profile;
    }
}
