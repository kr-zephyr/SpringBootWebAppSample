package com.sz21c.springbootwebappsample.config.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class HsqldbDatasourceConfig {

    @Bean
    public DataSource dataSourceForMyBatisUsingEmbedded() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder()
                .setName("springbootwebappsampledb")
                .addScript("classpath:hsqldb-init-sql/create-table.sql")
                .addScript("classpath:hsqldb-init-sql/insert-data.sql");
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
    }

}
