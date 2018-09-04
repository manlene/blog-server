package com.mm.blog.service;

import com.mm.blog.command.ArticleCommand;
import com.mm.blog.dao.ActicleDao;
import com.mm.blog.entity.Article;
import com.mm.blog.entity.ArticleTag;
import com.mongodb.client.result.UpdateResult;

import java.util.List;

/**
 * @Auther: mm
 * @Date: 2018/9/1 11:00
 * @Description:
 */
public interface ArticleService {

    Article saveArticle(Article article);

    ArticleTag saveArticleTag(ArticleTag articleTag);

    /**
     * 根据条件查找文章(articleId必传)
     * @return
     */
    Article findOneArticle(Article article);

    List<Article> findArticleByCondition(Article article);

    /**
     * 更新文章
     * @param articleCommand
     * @return
     */
    UpdateResult updateArticle(ArticleCommand articleCommand);

    /**
     * 更新文章标题
     * @param articleTag
     * @return
     */
   // ArticleTag updateArticleTag(ArticleTag articleTag);

}
