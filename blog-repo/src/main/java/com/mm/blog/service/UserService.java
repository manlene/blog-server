package com.mm.blog.service;

import com.mm.blog.entity.User;

import java.util.List;

/**
 * @Auther: mm
 * @Date: 2018/8/26 15:56
 * @Description:
 */
public interface UserService {
    /**
     * 根据邮箱和密码查找用户是否存在
     *
     * @param email
     * @param password
     * @return
     */
    User findUserByEmailAndPassword(String email, String password);

    /**
     * 注册用户
     *
     * @param user
     */
    void saveUser(User user);

    /**
     * 查询所有的用户
     * @return
     */
    List<User> findAllUser();

    /**
     * 通过用户id查找用户
     */
    User findUserById(String id);

    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);
}
