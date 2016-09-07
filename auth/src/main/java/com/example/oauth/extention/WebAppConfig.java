package com.example.oauth.extention;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by IBM on 2016/9/7.
 * web mvc 配置适配器
 */
@Configuration
@EnableWebMvc
public class WebAppConfig extends WebMvcConfigurerAdapter {

    /**
     * 请求认证适配器
     * @return
     */
    @Bean
    public AuthAdapter AuthAdapter(){
        return new AuthAdapter();
    }

    /**
     * 添加拦截器
     * 添加认证适配器做为请求拦截
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(AuthAdapter());
    }

}
