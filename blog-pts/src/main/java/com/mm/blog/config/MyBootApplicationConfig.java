package com.mm.blog.config;


import com.mm.blog.bind.LoginUserHandlerMethodArgumentResolver;
import com.mm.blog.intercepter.NeedLoginIntercepter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @Auther: mm
 * @Date: 2018/8/30 16:23
 * @Description:
 */
@Configuration
public class MyBootApplicationConfig implements WebMvcConfigurer {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyBootApplicationConfig.class);

    public void addInterceptors(InterceptorRegistry registry) {
        LOGGER.info("===========   拦截器注册开始===========");
        registry.addInterceptor(new NeedLoginIntercepter());
        LOGGER.info("===========   拦截器注册完毕   ===========");
    }

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginUserHandlerMethodArgumentResolver());
    }

}
