package com.mm.blog.bind;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Auther: mm
 * @Date: 2018/8/30 14:35
 * @Description: needLogin注解
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
//在jvm加载class时候有效, VM将在运行期也保留注释,因此可以通过反射机制读取注解的信息
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedLogin {
}
