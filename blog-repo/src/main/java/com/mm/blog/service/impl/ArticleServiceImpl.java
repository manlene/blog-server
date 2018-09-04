package com.mm.blog.service.impl;

import com.mm.blog.command.ArticleCommand;
import com.mm.blog.dao.ActicleDao;
import com.mm.blog.dao.ArticleTagDao;
import com.mm.blog.entity.Article;
import com.mm.blog.entity.ArticleTag;
import com.mm.blog.service.ArticleService;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.util.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: mm
 * @Date: 2018/9/1 11:01
 * @Description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ActicleDao articleDao;

    @Autowired
    private ArticleTagDao articleTagDao;

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    public Article saveArticle(Article article) {

        return articleDao.save(article);
    }

    public ArticleTag saveArticleTag(ArticleTag articleTag) {

        return articleTagDao.save(articleTag);
    }

    public Article findOneArticle(Article article) {
        Criteria criteria=Criteria.where("_id").is(article.getId());
        if (!StringUtils.isEmpty(article.getUserId()))
        criteria.and("userId").is(article.getUserId());
        Query query=new Query(criteria);
        return mongoTemplate.findOne(query,Article.class);
    }

    public List<Article> findArticleByCondition(Article article) {
        Criteria criteria=Criteria.where("userId").is(article.getUserId());
        Query query=new Query(criteria);
        return mongoTemplate.find(query,Article.class);
    }

    public UpdateResult updateArticle(ArticleCommand articleCommand) {
        Update update=new Update();
        Criteria updateCriteria = Criteria.where("_id").is(articleCommand.getId());
        if (!StringUtils.isEmpty(articleCommand.getArticleContent()))
            update.set("articleContent",articleCommand.getArticleContent());
        if (!StringUtils.isEmpty(articleCommand.getStatus()))
            update.set("status",articleCommand.getStatus());
        if (!StringUtils.isEmpty(articleCommand.getArticleTitle()))
            update.set("articleTitle",articleCommand.getArticleTitle());
        Query query = new Query(updateCriteria);
        return mongoTemplate.updateFirst(query,update,Article.class);
    }

}
