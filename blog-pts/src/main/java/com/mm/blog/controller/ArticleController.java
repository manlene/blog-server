package com.mm.blog.controller;

import com.mm.blog.bind.LoginUser;
import com.mm.blog.command.ArticleCommand;
import com.mm.blog.controller.handler.ArticleHandler;
import com.mm.blog.entity.Article;
import com.mm.blog.entity.User;
import com.mm.blog.response.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @Auther: mm
 * @Date: 2018/9/1 11:09
 * @Description:
 */
@RestController
public class ArticleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleHandler articleHandler;

    /**
     * 保存文章
     *
     * @param article
     * @param user
     * @return
     */
    @PostMapping("/article/save")
    public APIResponse saveArticle(@RequestBody ArticleCommand article, @LoginUser User user) {
        article.setUserId(user.getId());
        return articleHandler.saveArticle(article);

    }

    @GetMapping("/article/{articleId}")
    public APIResponse<ArticleCommand> findOneArticleByCondition(@PathVariable String articleId, @LoginUser User user) {
        Article article = new Article();
        article.setId(articleId);
        article.setUserId(user.getId());
        return articleHandler.findArticleByCondition(article);

    }

    @GetMapping("/article")
    public APIResponse<List<ArticleCommand>> getArticleByCondition(@LoginUser User user) {
        Article article = new Article();
        article.setUserId(user.getId());
        return articleHandler.findALLArticleByCondition(article);
    }

    @PatchMapping("/article/update")
    public APIResponse<List<ArticleCommand>> updateArticle(@RequestBody ArticleCommand articleCommand, @LoginUser User user) {
        articleCommand.setUserId(user.getId());
        return articleHandler.updateArticle(articleCommand);
    }

    @PostMapping(value = "/article/uploadImage")
    public APIResponse uploadImage(@RequestParam(value = "image") MultipartFile image) throws RuntimeException {
        return articleHandler.uploadImage(image);
    }

}
