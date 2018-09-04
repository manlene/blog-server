package com.mm.blog;

import com.mm.blog.entity.Article;
import com.mm.blog.service.ArticleService;
import com.mm.blog.util.MarkDown2HtmlWrapper;
import com.mm.blog.util.MarkdownEntity;
import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.options.MutableDataSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Auther: mm
 * @Date: 2018/9/1 19:10
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ArticleTest {

    @Autowired
    private ArticleService articleService;

    @Test
    public void ttttt() {
        Article article = new Article();
        article.setId("5b8a9744f0dcc88d68a93a59");
        Article articleDb = articleService.findOneArticle(article);
        MarkdownEntity markdownEntity = MarkDown2HtmlWrapper.ofContent(articleDb.getArticleContent());

        System.out.println(markdownEntity.toString());
    }
}
