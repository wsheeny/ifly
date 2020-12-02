// package com.wcy.rhapsody.admin.elastic.repository;
//
// import com.wcy.rhapsody.admin.mapper.TopicMapper;
// import com.wcy.rhapsody.admin.model.entity.Topic;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;
//
// import java.util.Iterator;
// import java.util.List;
//
// @SpringBootTest
// class TopicRepositoryTest {
//
//
//     @Autowired
//     private TopicRepository topicRepository;
//
//     @Autowired
//     private TopicMapper topicMapper;
//
//     @Test
//     void importAll() {
//         List<Topic> topicList = topicMapper.selectList(null);
//
//         Iterable<Topic> topics = topicRepository.saveAll(topicList);
//         Iterator<Topic> iterator = topics.iterator();
//         // int result = 0;
//         // while (iterator.hasNext()) {
//         //     result++;
//         //     iterator.next();
//         // }
//         // System.out.println(result);
//     }
// }