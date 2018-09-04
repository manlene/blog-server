package com.mm.blog.dao;

import com.mm.blog.entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: mm
 * @Date: 2018/8/30 14:54
 * @Description:
 */
@Repository
public interface TagDao extends MongoRepository<Tag, String> {
}
