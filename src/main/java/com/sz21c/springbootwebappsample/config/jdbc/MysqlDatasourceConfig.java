package com.sz21c.springbootwebappsample.config.jdbc;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.sql.DataSource;

@EnableConfigurationProperties(MysqlProperties.class)
public class MysqlDatasourceConfig {

    @Autowired
    private MysqlProperties properties;

    /**
     * 외부 DB(MySql 등)를 사용하는 경우 이 Method를 활성화시킨다.
     * Profile의 application.properties에서 DB 정보는 반드시 입력된 후...
     */
    public DataSource dataSourceForMyBatis() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(properties.getDriverClassName());
        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUsername());
        dataSource.setPassword(properties.getPassword());

        dataSource.setInitialSize(properties.getInitialSize());
        dataSource.setMaxIdle(properties.getMaxIdle());
        dataSource.setMaxActive(properties.getMaxActive());
        dataSource.setValidationQuery(properties.getValidationQuery());
        dataSource.setTestWhileIdle(properties.isTestWhileIdle());
        dataSource.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRunsMillis());

        return dataSource;
    }

}
