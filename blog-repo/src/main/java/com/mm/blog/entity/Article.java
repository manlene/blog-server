package com.mm.blog.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: mm
 * @Date: 2018/8/26 11:31
 * @Description: 文章实体类
 */
@Document(collection = "article")//指定表名
public class Article implements Serializable {

    private static final long serialVersionUID = 2350289533171241890L;
    @Id
    private String id;

    private String articleTitle;

    private String articleContent;

    private Date createTime;

    private String userId;

    private Integer status;

    /**
     * 未发布状态：2
     **/
    public static final Integer STATUS_NOT_PUBLISH = 2;
    /**
     * 失效状态：0
     **/
    public static final Integer STATUS_INVALID = 0;
    /**
     * 已发布状态：1
     **/
    public static final Integer STATUS_PUBLISH = 1;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(String articleContent) {
        this.articleContent = articleContent;
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
