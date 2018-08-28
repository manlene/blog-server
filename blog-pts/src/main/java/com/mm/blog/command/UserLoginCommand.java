package com.mm.blog.command;




import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: mm
 * @Date: 2018/8/27 16:40
 * @Description:用户登陆command
 */
public class UserLoginCommand implements Serializable {


    private static final long serialVersionUID = 2839531005100533008L;
    private String id;

    private String loginName;

    private String email;

    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
