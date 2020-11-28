package com.wcy.rhapsody.admin.service.api.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.api.FollowMapper;
import com.wcy.rhapsody.admin.model.entity.web.Follow;
import com.wcy.rhapsody.admin.service.api.FollowService;
import org.springframework.stereotype.Service;

/**
 * 关注实现类
 *
 * @author Yeeep 2020/11/20
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, Follow> implements FollowService {
}
