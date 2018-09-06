package com.mm.blog.service;

import com.mm.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @Auther: manman
 * @Date: 2018/9/6 11:26
 * @Description:
 */
@Service
public class SpringSecutityUserservice  implements UserDetailsService {

    @Autowired
    private  UserService userService;

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user= userService.findUserByUsername(s);
        return user;
    }
}
