package com.mm.blog.service.impl;

import com.mm.blog.dao.TagDao;
import com.mm.blog.entity.ArticleTag;
import com.mm.blog.entity.Tag;
import com.mm.blog.service.TagService;
import com.mongodb.client.result.DeleteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: mm
 * @Date: 2018/8/30 17:36
 * @Description:
 */
@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private TagDao tagDao;

    public void saveTag(Tag tag) {
        tagDao.save(tag);
    }

    public List<Tag> findTagsByUserId(String userId, Integer status) {
        Criteria criteria = null;
        if (!StringUtils.isEmpty(status))
            criteria = Criteria.where("userId").is(userId).and("status").is(status);
        criteria = Criteria.where("userId").is(userId);
        Query query = new Query(criteria);
        return mongoTemplate.find(query, Tag.class);
    }

    public void updateTagById(Tag tag) {
        Criteria criteria = Criteria.where("_id").is(tag.getId());
        Update update = new Update();
        if (!StringUtils.isEmpty(tag.getTagName()))
            update.set("tagName", tag.getTagName());
        if (!StringUtils.isEmpty(tag.getStatus()))
            update.set("status", tag.getStatus() == Tag.STATUS_ACTIVE ? Tag.STATUS_INVALID : Tag.STATUS_ACTIVE);
        Query query = new Query(criteria);
        mongoTemplate.updateFirst(query, update, Tag.class);
    }

    public List<Tag> findTagsByArticleId(String articleId) {
        List<ArticleTag> articleTags = mongoTemplate.find(Query.query(Criteria.where("articleId").is(articleId)), ArticleTag.class);
        List<String> tagIds=new ArrayList<String>();
        for (ArticleTag articleTag :articleTags){
            tagIds.add(articleTag.getTagId());
        }
        return  mongoTemplate.find(Query.query(Criteria.where("_id").in(tagIds)), Tag.class);
    }

    public DeleteResult deleteTagByArticleId(String articleId) {
        Query query = Query.query(Criteria.where("articleId").is(articleId));
        return mongoTemplate.remove(query,ArticleTag.class);
    }
}
