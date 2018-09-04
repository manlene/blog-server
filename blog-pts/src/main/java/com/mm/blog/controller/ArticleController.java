package com.mm.blog.controller;

import com.mm.blog.bind.LoginUser;
import com.mm.blog.command.ArticleCommand;
import com.mm.blog.command.UserLoginCommand;
import com.mm.blog.controller.handler.ArticleHandler;
import com.mm.blog.entity.Article;
import com.mm.blog.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: mm
 * @Date: 2018/9/1 11:09
 * @Description:
 */
@RestController
public class ArticleController {

    @Autowired
    private ArticleHandler articleHandler;
    /**
     * 保存文章
     * @param article
     * @param userLoginCommand
     * @return
     */
    @PostMapping("/article/save")
    public APIResponse saveArticle(@RequestBody ArticleCommand article, @LoginUser UserLoginCommand userLoginCommand){
        return articleHandler.saveArticle(article,userLoginCommand);

    }

    @GetMapping("/article/{articleId}")
    public APIResponse<ArticleCommand> findOneArticleByCondition(@PathVariable String  articleId, @LoginUser UserLoginCommand userLoginCommand){
        Article article=new Article();
        article.setId(articleId);
        article.setUserId(userLoginCommand.getId());
        return articleHandler.findArticleByCondition(article);

    }
    @GetMapping("/article")
    public APIResponse<List<ArticleCommand>> getArticleByCondition(@LoginUser UserLoginCommand userLoginCommand) {
        Article article=new Article();
        article.setUserId(userLoginCommand.getId());
        return articleHandler.findALLArticleByCondition(article);
    }
    @PatchMapping("/article/update")
    public APIResponse<List<ArticleCommand>> updateArticle(@RequestBody ArticleCommand articleCommand,@LoginUser UserLoginCommand userLoginCommand) {
        articleCommand.setUserId(userLoginCommand.getId());
        return articleHandler.updateArticle(articleCommand);
    }


}
