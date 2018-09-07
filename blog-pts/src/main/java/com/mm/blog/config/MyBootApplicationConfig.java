package com.mm.blog.config;


import com.mm.blog.bind.LoginUserHandlerMethodArgumentResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
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

    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginUserHandlerMethodArgumentResolver());
    }

}
