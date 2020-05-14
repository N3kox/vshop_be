package com.myshop.prototype.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.io.FileNotFoundException;

@Configuration
public class imageConfig implements WebMvcConfigurer {
    //图片反馈
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //5.14.2020 : server fix
        //registry.addResourceHandler("/image/item/**").addResourceLocations("classpath:/static/");
        //5.14.2020 : server total
        registry.addResourceHandler("/image/item/**").addResourceLocations("file:/home/ubuntu/xzn/static/");
    }
}
