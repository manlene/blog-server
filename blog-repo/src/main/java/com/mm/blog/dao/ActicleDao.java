package com.mm.blog.dao;

import com.mm.blog.entity.Article;
import com.mongodb.Mongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: mm
 * @Date: 2018/9/1 10:56
 * @Description:
 */
@Repository
public interface ActicleDao  extends MongoRepository<Article,String> {
}
