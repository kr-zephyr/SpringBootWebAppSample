package com.sz21c.springbootwebappsample.config.mybatis;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * MyBatis의 sqlmap을 Mapper 기반으로 사용하기 위해 MapperScan을 추가한다.
 */
@Configuration
@MapperScan({
    "com.sz21c.springbootwebappsample.sampleservice.mapper"
})
@EnableConfigurationProperties(MyBatisProperties.class)
public class MyBatisConfig {

    @Autowired
    private MyBatisProperties properties;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryForMyBatis(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(properties.getConfigLocation());
        sqlSessionFactoryBean.setConfigurationProperties(getMyBatisProperties());

        return sqlSessionFactoryBean;
    }

    /**
     * MyBatis 내부 속성 설정 정의
     *
     * cacheEnabled : MyBatis의 내부 Cache 활성화
     * lazeLoadingEnabled : 서브 쿼리 등으로 N + 1 쿼리 실행이 발생되는 환경은 getter 등으로 데이터가 호출되는 시점에 쿼리가 실행되도록 설정
     * defaultExecutorType : SQL 실행 모드를 기본으로 Batch 형태로 실행되도록 설정
     */
    private Properties getMyBatisProperties() {
        Properties properties = new Properties();
        properties.put("cacheEnabled", true);
        properties.put("lazyLoadingEnabled", true);
        properties.put("defaultExecutorType", "BATCH");

        return properties;
    }

    @Bean
    public SqlSession sqlSessionForMyBatis(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

}
