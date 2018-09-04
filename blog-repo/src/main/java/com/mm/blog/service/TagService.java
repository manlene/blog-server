package com.mm.blog.service;

import com.mm.blog.entity.Tag;
import com.mongodb.client.result.DeleteResult;

import java.util.List;

/**
 * @Auther: mm
 * @Date: 2018/8/30 14:55
 * @Description:
 */
public interface TagService {

    void saveTag(Tag tag);

    /**
     * 根据userId和status查询所有标签
     * @param userId
     * @param status
     * @return
     */
    List<Tag> findTagsByUserId (String userId,Integer status);


    void updateTagById(Tag tag);

    List<Tag> findTagsByArticleId (String articleId);

    DeleteResult deleteTagByArticleId(String articleId);
}
