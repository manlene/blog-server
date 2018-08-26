package com.mm.blog.service.impl;

import com.mm.blog.dao.UserDao;
import com.mm.blog.entity.User;
import com.mm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

/**
 * @Auther: mm
 * @Date: 2018/8/26 16:02
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private UserDao userDao;

    public User findUserByEmailAndPassword(User user) {
        Criteria emailCriteria = Criteria.where("email").is(user.getEmail());
        Criteria pwdCriteria = Criteria.where("password").is(user.getPassword());
        Criteria cr = new Criteria();
        Query query = new Query(cr.andOperator(emailCriteria, pwdCriteria));
        User userDb = mongoTemplate.findOne(query, User.class);
//        Criteria emailCriteria = Criteria.where("email").is(user.getEmail())
//                .and("password").is(user.getPassword());
//        Query query = new Query(emailCriteria);
//        User userDb = mongoTemplate.findOne(query, User.class);
        return userDb;
    }

    public void saveUser(User user) {
        userDao.save(user);
      // mongoTemplate.save(user);
    }
}
