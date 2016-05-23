package com.sz21c.springbootwebappsample.config.hibernate;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "hibernate")
public class HibernateProperties {
    private String dialect;
    private String showSql;
    private String formatSql;
}
