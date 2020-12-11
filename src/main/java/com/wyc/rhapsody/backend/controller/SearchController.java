package com.wyc.rhapsody.backend.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wyc.rhapsody.backend.common.R;
import com.wyc.rhapsody.backend.model.vo.TopicVO;
import com.wyc.rhapsody.backend.service.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Knox
 * @date 2020/12/5
 */
@RestController
@RequestMapping("/api/search")
public class SearchController extends BaseController {

    @Autowired
    private TopicService topicService;


    /**
     * 关键字检索
     *
     * @param keyword
     * @return
     */
    @GetMapping()
    public R searchList(@RequestParam("keyword") String keyword,
                        @RequestParam("pageNum") Integer pageNum,
                        @RequestParam("pageSize") Integer pageSize) {
        Page<TopicVO> results = topicService.searchByKey(keyword, new Page<>(pageNum, pageSize));
        return R.ok().data(results);
    }

}
