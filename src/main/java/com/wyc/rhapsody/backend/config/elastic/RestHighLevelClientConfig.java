package com.wyc.rhapsody.backend.config.elastic;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * 最新版已弃用yaml配置
 * <p>
 * # 最新版已启用，使用config
 * #spring.data.elasticsearch.cluster-name=my-application
 * #spring.data.elasticsearch.cluster-nodes=http://127.0.0.1:9300
 *
 * @author Knox
 * @date 2020/12/2
 */
@Configuration
public class RestHighLevelClientConfig extends AbstractElasticsearchConfiguration {

    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("localhost:9200", "127.0.0.1:9200")
                .build();

        return RestClients.create(clientConfiguration).rest();
    }
}
