package com.sz21c.springbootwebappsample.config.mybatis;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * MyBatis의 sqlmap을 Mapper 기반으로 사용하기 위해 MapperScan을 추가한다.
 */
@Configuration
@MapperScan("com.sz21c.springbootwebappsample.sampleservice.mapper")
public class MyBatisConfig {

    @Autowired
    private MyBatisProperties properties;

    @Bean
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

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryForMyBatis(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(properties.getConfigLocation());
        return sqlSessionFactoryBean;
    }

    @Bean
    public SqlSession sqlSessionForMyBatis(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
