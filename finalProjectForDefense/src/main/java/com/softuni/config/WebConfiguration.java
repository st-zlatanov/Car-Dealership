package com.softuni.config;

import com.softuni.web.interceptor.FaviconInterceptor;
import com.softuni.web.interceptor.PageTitleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private final FaviconInterceptor faviconInterceptor;
    private final PageTitleInterceptor titleInterceptor;


    public WebConfiguration(FaviconInterceptor faviconInterceptor, PageTitleInterceptor titleInterceptor) {
        this.faviconInterceptor = faviconInterceptor;
        this.titleInterceptor = titleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.faviconInterceptor);
        registry.addInterceptor(this.titleInterceptor);
    }
}
