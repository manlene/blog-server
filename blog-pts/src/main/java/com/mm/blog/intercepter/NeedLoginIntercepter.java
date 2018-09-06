package com.mm.blog.intercepter;

import com.mm.blog.bind.NeedLogin;
import com.mm.blog.command.UserLoginCommand;
import com.mm.blog.constants.BlogConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: mm
 * @Date: 2018/8/29 21:30
 * @Description: 是否登录拦截器
 */

@Component
public class NeedLoginIntercepter  implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String a=request.getParameter("username");
        String b=request.getParameter("password");
        if (handler instanceof HandlerMethod){

            HandlerMethod method = (HandlerMethod) handler;
            NeedLogin needLogin=  method.getMethodAnnotation(NeedLogin.class);
            if (needLogin == null){
                needLogin = method.getMethod().getDeclaringClass().getAnnotation(NeedLogin.class);
            }
            if (needLogin != null){
                return validateAccess(request, response, handler);
            }
        }
        return true;
    }

    private boolean validateAccess(HttpServletRequest request, HttpServletResponse response, Object handler){
        UserLoginCommand userLoginCommand =  (UserLoginCommand)request.getSession().getAttribute(BlogConstants.USER_DETAILS_SESSION);
        if (userLoginCommand==null){
            return false;
        }
        return true;
    }
}
