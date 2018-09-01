package com.mm.blog.controller;


import com.mm.blog.Main;
import com.mm.blog.bind.NeedLogin;
import com.mm.blog.command.UserLoginCommand;
import com.mm.blog.command.UserRegisterCommand;
import com.mm.blog.controller.handler.UserHandler;
import com.mm.blog.entity.User;
import com.mm.blog.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 用户controller
 */
@RestController
public class UserController {

    @Autowired
    private UserHandler userHandler;

    /**
     * 用户登录
     * @param request
     * @param email
     * @param password
     * @return
     */
    @GetMapping("/user/login")
    public APIResponse<UserLoginCommand> userLogin(HttpServletRequest request, @RequestParam String email, @RequestParam String password) {

        return userHandler.userLogin(request,email,password);
    }

    /**
     * 用户新增
     * @param userRegisterCommand
     * @return
     */
    @PostMapping("/user/save")
    public APIResponse saveUser(@RequestBody UserRegisterCommand userRegisterCommand ) {

        return userHandler.saveUser(userRegisterCommand);
    }

    /**
     * 查询所有的用户
     * @return
     */
    @GetMapping("/user")

    public APIResponse<List<UserLoginCommand>> findAllUser() {

        return userHandler.findAllUser();
    }

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    @GetMapping("/user/{id}")
    public APIResponse<User> saveUser(@PathVariable String id ) {

        return userHandler.findUserById(id);
    }

    /**
     * 修改用户
     * @param userRegisterCommand
     * @return
     */
    @PatchMapping("/user/update")
    public APIResponse<User> updateUserStatus(@RequestBody UserRegisterCommand userRegisterCommand) {

        return userHandler.updateUserStatus(userRegisterCommand);
    }

}
