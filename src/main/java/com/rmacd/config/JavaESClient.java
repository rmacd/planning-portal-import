package com.rmacd.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class JavaESClient {

    @Value("${spring.elasticsearch.username}")
    String esUser;

    @Value("${spring.elasticsearch.password}")
    String esPass;

    @Value("${spring.elasticsearch.uris}")
    String esHost;

    @Bean
    ElasticsearchClient getClient() {
        RestClient restClient = RestClient.builder(new HttpHost(esHost, 443, "https")).build();
        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        return new ElasticsearchClient(transport);
    }
}

