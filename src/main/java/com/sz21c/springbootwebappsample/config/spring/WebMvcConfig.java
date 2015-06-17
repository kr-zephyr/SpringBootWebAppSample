package com.sz21c.springbootwebappsample.config.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class WebMvcConfig {

    /**
     * 기본 servlet handler를 지정한다.
     */
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /**
     *
     * <pre>
     * view resolver를 설정한다.
     * 이 예제에서는 InternalResourceViewResolver를 사용한다.
     * InternalResourceViewResolver는 WEB-INF 내부의 지정된 영역(prefix)의 지정된 확장자(suffix)를 가진 파일로
     * controller의 return을 전달한다.
     * InternalResourceViewResolver에서 지정된 영역은 외부에서 url 등으로 접근이 불가능한 영역이다.
     * </pre>
     *
     * @since 2015. 5. 12.
     * @return
     */
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

}
