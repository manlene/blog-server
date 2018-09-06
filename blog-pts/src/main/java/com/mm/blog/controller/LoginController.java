package com.mm.blog.controller;

import com.mm.blog.response.APIResponse;
import com.mm.blog.response.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: manman
 * @Date: 2018/9/6 11:12
 * @Description:
 */
@RestController
public class LoginController {


    @RequestMapping("/login_error")
    public APIResponse loginError() {
        return APIResponse.build(ResponseStatus.EMAIL_OR_PASSWORD_ERROR, null);
    }

    @RequestMapping("/login_success")
    public APIResponse loginSuccess() {
        return APIResponse.build(ResponseStatus.LOGIN_SUCCESS, null);
    }

    /**
     * 如果自动跳转到这个页面，说明用户未登录，返回相应的提示即可
     * <p>
     * 如果要支持表单登录，可以在这个方法中判断请求的类型，进而决定返回JSON还是HTML页面
     *
     * @return
     */
    @RequestMapping("/login")
    public APIResponse loginPage() {
        return APIResponse.build(ResponseStatus.NOT_LOGIN_EEROR, null);
    }

}
