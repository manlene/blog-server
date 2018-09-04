package com.mm.blog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: mm
 * @Date: 2018/8/26 11:42
 * @Description:标签实体类
 */
@Document(collection = "tag")//指定表名
public class Tag implements Serializable {


    private static final long serialVersionUID = -5432481462559390135L;
    /**
     * 生效状态：1
     **/
    public static final Integer STATUS_ACTIVE = 1;
    /**
     * 失效状态：0
     **/
    public static final Integer STATUS_INVALID = 0;
    @Id
    private String id;

    private String tagName;

    private Date createTime;

    private String userId;

    private Integer status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
