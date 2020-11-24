package com.wcy.rhapsody.admin.service.api.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wcy.rhapsody.admin.mapper.api.CategoryMapper;
import com.wcy.rhapsody.admin.modules.entity.web.Category;
import com.wcy.rhapsody.admin.modules.vo.TopicVO;
import com.wcy.rhapsody.admin.service.api.CategoryService;
import com.wcy.rhapsody.admin.service.api.TopicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 分类接口实现类
 *
 * @author Yeeep 2020/11/15
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Resource
    private TopicService topicService;

    @Override
    public IPage<TopicVO> listByCategoryName(Category category, Page<TopicVO> topicVOPage) {
        return topicService.selectTopicsByCategory(category, topicVOPage);
    }
}
