package com.spring.springboot.intercaptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConifg implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry){
    registry.addInterceptor(new MyIntercepotor())
            .addPathPatterns("/**")
            .excludePathPatterns("/login");
  }
}
