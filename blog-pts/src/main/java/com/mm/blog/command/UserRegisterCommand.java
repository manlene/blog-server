package com.mm.blog.command;

import java.io.Serializable;

/**
 * @Auther: mm
 * @Date: 2018/8/28 18:27
 * @Description:
 */
public class UserRegisterCommand implements Serializable {


    private static final long serialVersionUID = 6528678087069885488L;
    private String loginName;
    private String email;
    private String password;
    //重复密码
    private String repassword;
    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }
}
