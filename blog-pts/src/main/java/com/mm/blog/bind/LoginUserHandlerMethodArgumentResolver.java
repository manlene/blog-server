package com.mm.blog.bind;

import com.mm.blog.command.UserLoginCommand;
import com.mm.blog.constants.BlogConstants;
import org.bson.types.ObjectId;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: mm
 * @Date: 2018/8/30 16:04
 * @Description: loginUser注解获取session里面得用户信息
 */
public class LoginUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    /** redis 里面存放的 用户对象类型. */
    private Class<?> sessionUserClass = UserLoginCommand.class;

    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class);
    }

    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        Object user= request.getSession().getAttribute(BlogConstants.USER_DETAILS_SESSION);
        if (user==null) return null;
        //2.如果标识的是redis对象本身是配置的类型,那么直接获取返回;
        Class<?> klass = methodParameter.getParameterType();
        if (klass.isAssignableFrom(sessionUserClass)){
            return user;
        }
        return null;
    }
}
