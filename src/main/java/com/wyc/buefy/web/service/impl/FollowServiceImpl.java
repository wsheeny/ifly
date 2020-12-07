package com.wyc.buefy.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.buefy.web.mapper.FollowMapper;
import com.wyc.buefy.web.model.entity.TbFollow;
import com.wyc.buefy.web.service.FollowService;
import org.springframework.stereotype.Service;

/**
 * 关注实现类
 *
 * @author Knox 2020/11/20
 */
@Service
public class FollowServiceImpl extends ServiceImpl<FollowMapper, TbFollow> implements FollowService {
}
