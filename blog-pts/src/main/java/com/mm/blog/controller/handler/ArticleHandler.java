package com.mm.blog.controller.handler;

import com.mm.blog.command.ArticleCommand;
import com.mm.blog.controller.ArticleController;
import com.mm.blog.entity.Article;
import com.mm.blog.entity.ArticleTag;
import com.mm.blog.entity.Tag;
import com.mm.blog.response.APIResponse;
import com.mm.blog.response.ResponseStatus;
import com.mm.blog.service.ArticleService;
import com.mm.blog.service.TagService;
import com.mm.blog.util.MarkDown2HtmlWrapper;
import com.mm.blog.util.MarkdownEntity;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: mm
 * @Date: 2018/9/1 11:12
 * @Description:
 */
@Component
public class ArticleHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleHandler.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    @Value("${uploadDir}")
    private String uploadDir;

    @Value("${static.image.path}")
    private String imagePath;
    /**
     * 保存文章
     *
     * @param articleCommand
     * @return
     */
    public APIResponse saveArticle(ArticleCommand articleCommand) {
        if (StringUtils.isEmpty(articleCommand.getArticleTitle()) || StringUtils.isEmpty(articleCommand.getArticleContent()))
            return APIResponse.build(ResponseStatus.FAIL_PARAMETER_EXCEPTION, null);
        Article article = new Article();
        BeanUtils.copyProperties(articleCommand, article);
        article.setCreateTime(new Date());
        article.setStatus(Article.STATUS_NOT_PUBLISH);
        Article articleDb = articleService.saveArticle(article);
        List<String> tagIds = articleCommand.getTagIds();
        for (String tagId : tagIds) {
            ArticleTag articleTag = new ArticleTag();
            articleTag.setTagId(tagId);
            articleTag.setArticleId(articleDb.getId());
            articleService.saveArticleTag(articleTag);
        }
        if (StringUtils.isEmpty(articleDb)) return APIResponse.build(ResponseStatus.SAVE_ARTICLE_ERROR, null);
        return APIResponse.build(ResponseStatus.SAVE_ARTICLE_SUCCESS, null);
    }

    public APIResponse<ArticleCommand> findArticleByCondition(Article article) {
        if (StringUtils.isEmpty(article.getId()))
            return APIResponse.build(ResponseStatus.FAIL_PARAMETER_EXCEPTION, null);
        Article articleDB = articleService.findOneArticle(article);
        MarkdownEntity markdownEntity = MarkDown2HtmlWrapper.ofContent(articleDB.getArticleContent());

        ArticleCommand articleCommand = new ArticleCommand();
        BeanUtils.copyProperties(articleDB, articleCommand);
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        articleCommand.setCreateTime(sf.format(articleDB.getCreateTime()));
        articleCommand.setArticleContentHtml(markdownEntity.toString());
        List<Tag> tags = tagService.findTagsByArticleId(articleDB.getId());
        articleCommand.setTags(tags);
        return APIResponse.build(ResponseStatus.FIND_ARTICLE_SUCCESS, articleCommand);
    }

    public APIResponse<List<ArticleCommand>> findALLArticleByCondition(Article article) {
        if (StringUtils.isEmpty(article.getUserId()))
            return APIResponse.build(ResponseStatus.FAIL_PARAMETER_EXCEPTION, null);
        List<Article> articles = articleService.findArticleByCondition(article);
        List<ArticleCommand> articleCommands = new ArrayList<ArticleCommand>();
        for (Article articleDB : articles) {
            MarkdownEntity markdownEntity = MarkDown2HtmlWrapper.ofContent(articleDB.getArticleContent());
            ArticleCommand articleCommand = new ArticleCommand();
            BeanUtils.copyProperties(articleDB, articleCommand);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            articleCommand.setCreateTime(sf.format(articleDB.getCreateTime()));
            articleCommand.setArticleContentHtml(markdownEntity.toString());
            List<Tag> tags = tagService.findTagsByArticleId(articleCommand.getId());
            articleCommand.setTags(tags);
            if (Article.STATUS_INVALID.equals(articleDB.getStatus())) {
                articleCommand.setStatusStr("失效");
            } else if (Article.STATUS_PUBLISH.equals(articleDB.getStatus())) {
                articleCommand.setStatusStr("已发布");
            } else if (Article.STATUS_NOT_PUBLISH.equals(articleDB.getStatus())) {
                articleCommand.setStatusStr("未发布");
            }
            articleCommands.add(articleCommand);
        }
        return APIResponse.build(ResponseStatus.FIND_ARTICLE_SUCCESS, articleCommands);
    }

    public APIResponse updateArticle(ArticleCommand articleCommand) {
        UpdateResult updateResult = articleService.updateArticle(articleCommand);
        //判断文章tag是否修改
        if (!StringUtils.isEmpty(articleCommand.getTagIds())) {
            //先删除之前的标签
            DeleteResult deleteResult = tagService.deleteTagByArticleId(articleCommand.getId());
            //再新增tag
            List<String> tagIds = articleCommand.getTagIds();
            for (String tagId : tagIds) {
                ArticleTag articleTag = new ArticleTag();
                articleTag.setTagId(tagId);
                articleTag.setArticleId(articleCommand.getId());
                articleService.saveArticleTag(articleTag);
            }
        }

        return APIResponse.build(ResponseStatus.UPDATE_ARTICLE_SUCCESS, null);
    }

    public APIResponse uploadImage(MultipartFile image) {

        if (image.isEmpty()) {
            return APIResponse.build(ResponseStatus.FILE_NULL_ERROR, null);
        }
        // 获取文件名
        String fileName = image.getOriginalFilename();
        LOGGER.info("上传的文件名为：" + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        LOGGER.info("上传的后缀名为：" + suffixName);
        // 文件上传后的路径
        String filePath = uploadDir;
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            image.transferTo(dest);
            LOGGER.info("上传成功后的文件路径：" + filePath + fileName);
            return APIResponse.build(ResponseStatus.FILE_UPLOAD_SUCCESS, imagePath+fileName);
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return APIResponse.build(ResponseStatus.FILE_UPLOAD_ERROR, null);

    }
}
