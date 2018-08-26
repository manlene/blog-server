package com.mm.blog.service;

import com.mm.blog.entity.User;

/**
 * @Auther: mm
 * @Date: 2018/8/26 15:56
 * @Description:
 */
public interface UserService {
    /**
     * 根据邮箱和密码查找用户是否存在
     * @param user
     * @return
     */
    User findUserByEmailAndPassword(User user);

    /**
     * 注册用户
     * @param user
     */
    void saveUser(User user);
}
