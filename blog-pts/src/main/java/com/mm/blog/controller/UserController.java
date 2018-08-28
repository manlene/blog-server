package com.mm.blog.controller;


import com.mm.blog.command.UserLoginCommand;
import com.mm.blog.command.UserRegisterCommand;
import com.mm.blog.controller.handler.UserHandler;
import com.mm.blog.entity.User;
import com.mm.blog.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/user/login")
    public APIResponse<UserLoginCommand> userLogin(HttpServletRequest request, @RequestParam String email, @RequestParam String password) {

        return userHandler.userLogin(request,email,password);
    }

    @PostMapping("/user/save")
    public APIResponse saveUser(@RequestBody UserRegisterCommand userRegisterCommand ) {

        return userHandler.saveUser(userRegisterCommand);
    }
    @GetMapping("/user")
    public APIResponse<List<UserLoginCommand>> findAllUser() {

        return userHandler.findAllUser();
    }
}
