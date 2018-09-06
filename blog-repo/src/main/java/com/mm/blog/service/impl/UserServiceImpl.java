package com.mm.blog.service.impl;

import com.mm.blog.dao.UserDao;
import com.mm.blog.entity.User;
import com.mm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    public User findUserByEmailAndPassword(String email, String password) {
        Criteria emailCriteria = Criteria.where("email").is(email)
                .and("password").is(password).and("status").is(User.STATUS_ACTIVE);
        Query query = new Query(emailCriteria);
        User userDb = mongoTemplate.findOne(query, User.class);
        return userDb;
    }

    public void saveUser(User user) {
        userDao.save(user);

    }

    public List<User> findAllUser() {
        return mongoTemplate.findAll(User.class);
    }

    public User findUserById(String id) {
        return mongoTemplate.findById(id,User.class);
    }

    /**
     * 更新用户信息
     * @param user
     */
    public void updateUser(User user) {
        Criteria updateCriteria = Criteria.where("_id").is(user.getId());
        Update update=new Update();
        if (!StringUtils.isEmpty(user.getLoginName()))
            update.set("loginName",user.getLoginName());
        if (!StringUtils.isEmpty(user.getPassword()))
            update.set("password",user.getPassword());
        if (!StringUtils.isEmpty(user.getEmail()))
            update.set("email",user.getEmail());
        if (!StringUtils.isEmpty(user.getStatus()))
        update.set("status",user.getStatus().equals(User.STATUS_ACTIVE)?User.STATUS_INVALID:User.STATUS_ACTIVE);
        Query query = new Query(updateCriteria);
        mongoTemplate.updateFirst(query, update,User.class);
    }

    public User findUserByUsername(String username) {
        Criteria emailCriteria = Criteria.where("email").is(username).and("status").is(User.STATUS_ACTIVE);
        Query query = new Query(emailCriteria);
        User userDb = mongoTemplate.findOne(query, User.class);
        return userDb;
    }
}
