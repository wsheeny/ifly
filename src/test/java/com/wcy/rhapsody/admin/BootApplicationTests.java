package com.wcy.rhapsody.admin;

import com.wcy.rhapsody.admin.mapper.api.TopicMapper;
import com.wcy.rhapsody.admin.modules.entity.web.Topic;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private TopicMapper topicMapper;

    @Test
    void testUpdate() {
        Topic topic = topicMapper.selectById("1331577537143721986");
        topic.setContent("hi test");
        topicMapper.updateById(topic);

    }


}
