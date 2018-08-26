package com.mm.blog.dao;

import com.mm.blog.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @Auther: mm
 * @Date: 2018/8/26 12:29
 * @Description:
 */
@Repository
public interface UserDao extends MongoRepository<User, String> {

    User findUserByEmailAndPassword(User user);
}
