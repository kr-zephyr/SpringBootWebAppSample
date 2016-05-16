package com.sz21c.springbootwebappsample.config.spring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.stereotype.Component;

@Component
public class CustomizationConfig {

    @Value("${server.port}")
    private Integer port;

    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(port);
    }

}
