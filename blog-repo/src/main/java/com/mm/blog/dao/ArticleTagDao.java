package com.mm.blog.dao;

import com.mm.blog.entity.ArticleTag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: mm
 * @Date: 2018/9/1 17:23
 * @Description:
 */
@Repository
public interface ArticleTagDao extends MongoRepository<ArticleTag,String> {
}
