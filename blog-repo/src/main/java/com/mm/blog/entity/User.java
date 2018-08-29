package com.mm.blog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;


/**
 * @Auther: mm
 * @Date: 2018/8/26 11:31
 * @Description: 用户实体类
 */
@Document(collection = "user")//指定表名
public class User implements Serializable {


    private static final long serialVersionUID = 6217285991651220043L;
    @Id
    private String id;

    private String loginName;

    private String email;

    private String password;

    private Date createTime;

    private Integer status;
    /**激活状态：1**/
    public static final Integer STATUS_ACTIVE=1;
    /**失效状态：0**/
    public static final Integer STATUS_INVALID=0;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
