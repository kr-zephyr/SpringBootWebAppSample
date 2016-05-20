package com.sz21c.springbootwebappsample.config.mybatis;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * MyBatis의 sqlmap을 Mapper 기반으로 사용하기 위해 MapperScan을 추가한다.
 */
@Configuration
@MapperScan("com.sz21c.springbootwebappsample.sampleservice.mapper")
@EnableConfigurationProperties(MyBatisProperties.class)
public class MyBatisConfig {

    @Autowired
    private MyBatisProperties properties;

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

    @Bean
    public DataSource dataSourceForMyBatisUsingEmbedded() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder()
            .setName("springbootwebappsampledb")
            .addScript("classpath:hsqldb-init-sql/create-table.sql")
            .addScript("classpath:hsqldb-init-sql/insert-data.sql");
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
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
