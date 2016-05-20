package com.sz21c.springbootwebappsample.config.jdbc;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "mysql")
public class MysqlProperties {

    private String driverClassName;
    private String url;
    private String username;
    private String password;

    private Integer initialSize;
    private Integer maxIdle;
    private Integer maxActive;
    private String validationQuery;
    private boolean testWhileIdle;
    private Long timeBetweenEvictionRunsMillis;

}
