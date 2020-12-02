package com.wyc.elegant.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyc.elegant.admin.mapper.CategoryMapper;
import com.wyc.elegant.admin.model.entity.Category;
import com.wyc.elegant.admin.model.vo.TopicVO;
import com.wyc.elegant.admin.service.CategoryService;
import com.wyc.elegant.admin.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 分类接口实现类
 *
 * @author Yeeep 2020/11/15
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private TopicService topicService;

    @Override
    public IPage<TopicVO> listByCategoryName(Category category, Page<TopicVO> topicVOPage) {
        return topicService.selectTopicsByCategory(category, topicVOPage);
    }
}
