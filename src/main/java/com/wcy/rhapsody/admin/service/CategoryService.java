package com.wcy.rhapsody.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wcy.rhapsody.admin.model.entity.Category;
import com.wcy.rhapsody.admin.model.vo.TopicVO;

/**
 * 分类接口
 *
 * @author Yeeep 2020/11/15
 */
public interface CategoryService extends IService<Category> {

    /**
     * 当前类目下的主题
     *
     * @param category
     * @param topicVOPage
     * @return
     */
    IPage<TopicVO> listByCategoryName(Category category, Page<TopicVO> topicVOPage);
}
