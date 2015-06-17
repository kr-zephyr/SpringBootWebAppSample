package com.sz21c.springbootwebappsample.config.spring;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.stereotype.Component;

@Component
public class CustomizationConfig {

    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8080);
    }

}
