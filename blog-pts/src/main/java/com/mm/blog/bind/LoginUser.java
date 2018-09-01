package com.mm.blog.bind;

import java.lang.annotation.*;

/**
 * @Auther: mm
 * @Date: 2018/8/30 15:09
 * @Description:
 */
@Documented
//在jvm加载class时候有效, VM将在运行期也保留注释,因此可以通过反射机制读取注解的信息
@Retention(RetentionPolicy.RUNTIME)

//仅用于 Method parameter
@Target({ ElementType.PARAMETER })
public @interface LoginUser {
}
