package com.mm.blog.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: mm
 * @Date: 2018/8/29 21:30
 * @Description: 是否登录拦截器
 */
public class NeedLoginIntercepter  implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
    }
}
