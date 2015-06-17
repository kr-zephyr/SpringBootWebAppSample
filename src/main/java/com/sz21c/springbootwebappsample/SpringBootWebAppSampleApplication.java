package com.sz21c.springbootwebappsample;

import com.sz21c.springbootwebappsample.common.properties.HttpTestProperties;
import com.sz21c.springbootwebappsample.config.mybatis.MyBatisProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.web.SpringBootServletInitializer;

import javax.annotation.PreDestroy;

@SpringBootApplication
@EnableConfigurationProperties({MyBatisProperties.class, HttpTestProperties.class})
@EnableAutoConfiguration(
        exclude = {
                org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class})
public class SpringBootWebAppSampleApplication extends SpringBootServletInitializer {

	final static Logger logger = LoggerFactory.getLogger(SpringBootWebAppSampleApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebAppSampleApplication.class, args);
    }
	
    @PreDestroy
    public void shutdownProc() {
		//TODO implement process before application shutdown
        logger.info("shutdown!!");
    }
}
