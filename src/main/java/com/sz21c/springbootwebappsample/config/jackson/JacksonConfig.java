package com.sz21c.springbootwebappsample.config.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sz21c.springbootwebappsample.common.properties.HttpTestProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zephyr on 15. 7. 3..
 */
@Configuration
@EnableConfigurationProperties(HttpTestProperties.class)
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        //** 받아들이려는 Model보다 큰 데이터 구조가 왔을 때, Model에 없는 필드 무시
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return objectMapper;
    }
}
