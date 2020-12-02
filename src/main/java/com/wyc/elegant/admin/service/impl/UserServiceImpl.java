package com.wyc.elegant.admin.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.crypto.digest.BCrypt;
import cn.hutool.extra.mail.MailUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.elegant.admin.component.RedisService;
import com.wyc.elegant.admin.mapper.ColumnMapper;
import com.wyc.elegant.admin.mapper.UserMapper;
import com.wyc.elegant.admin.model.dto.RegisterDTO;
import com.wyc.elegant.admin.model.entity.Column;
import com.wyc.elegant.admin.model.entity.Follow;
import com.wyc.elegant.admin.model.entity.Topic;
import com.wyc.elegant.admin.model.entity.User;
import com.wyc.elegant.admin.model.vo.ProfileVO;
import com.wyc.elegant.admin.service.FollowService;
import com.wyc.elegant.admin.service.TopicService;
import com.wyc.elegant.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * 用户 实现类
 *
 * @author Yeeep 2020/11/7
 */
@Service
@Transactional()
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private FollowService followService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private ColumnMapper columnMapper;

    @Value("${config.domain}")
    private String domain;

    @Override
    public User selectByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, username);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public void createUser(RegisterDTO dto) {
        // 是否在事务中
        // boolean actualTransactionActive = TransactionSynchronizationManager.isActualTransactionActive();


        User user = User.builder()
                .username(dto.getName())
                .alias(dto.getName())
                .password(BCrypt.hashpw(dto.getPass(), BCrypt.gensalt()))
                .email(dto.getEmail())
                .createTime(new Date())
                .build();
        baseMapper.insert(user);

        // 30分钟
        String activeCode = RandomUtil.randomString(8);
        redisService.set("activeCode[" + dto.getName() + "]", activeCode, 30 * 60);
        // 发送邮件
        String activeUrl = URLUtil.normalize(domain + "?user=" + dto.getName() + "&code=" + activeCode);
        String content = "请在30分钟内激活您的账号，如非本人操作，请忽略 </br > " +
                "<a href=\"" + activeUrl + "\" target =\"_blank\" '>点击激活账号</a>";
        MailUtil.send(dto.getEmail(), "【滚雪球】账号激活", content, true);
    }


    @Override
    public ProfileVO getUserProfile(String id) {
        ProfileVO profile = new ProfileVO();
        User user = this.baseMapper.selectById(id);
        BeanUtils.copyProperties(user, profile);
        // 用户文章数
        int count = topicService.count(new LambdaQueryWrapper<Topic>().eq(Topic::getUserId, id));
        profile.setTopicCount(count);

        // 粉丝数
        int followers = followService.count(new LambdaQueryWrapper<Follow>().eq(Follow::getParentId, id));
        profile.setFollowerCount(followers);
        // 关注数
        int follows = followService.count(new LambdaQueryWrapper<Follow>().eq(Follow::getFollowerId, id));
        profile.setFollowCount(follows);
        // 专栏数
        Integer integer = columnMapper.selectCount(new LambdaQueryWrapper<Column>().eq(Column::getUserId, id));
        profile.setColumns(integer);

        return profile;
    }
}
