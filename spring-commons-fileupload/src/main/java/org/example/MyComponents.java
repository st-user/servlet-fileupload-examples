package org.example;

import static org.example.Constants.FILE_SIZE_THRESHOLD;
import static org.example.Constants.MAX_FILE_SIZE;
import static org.example.Constants.MAX_REQUEST_SIZE;

import java.nio.charset.StandardCharsets;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Component
public class MyComponents {
    
    @Bean
    public CommonsMultipartResolver commonsMultipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        resolver.setMaxInMemorySize(FILE_SIZE_THRESHOLD);
        resolver.setMaxUploadSize(MAX_REQUEST_SIZE);
        resolver.setMaxUploadSizePerFile(MAX_FILE_SIZE);
        resolver.setResolveLazily(true);
        return resolver;
    }

    @Bean
    public FilterRegistrationBean<CharacterEncodingFilter> characterEncodingFilterRegistration() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding(StandardCharsets.UTF_8.name());
        filter.setForceEncoding(true);

        FilterRegistrationBean<CharacterEncodingFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(filter);
        bean.addUrlPatterns("/*");
        return bean;
    }
}
