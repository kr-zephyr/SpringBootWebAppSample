package com.sz21c.springbootwebappsample.config.mybatis;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

@Data
@ConfigurationProperties(prefix = "mybatis")
public class MyBatisProperties {
    private Resource configLocation;
}
