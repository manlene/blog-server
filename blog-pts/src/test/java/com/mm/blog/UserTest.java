package com.mm.blog;

import com.mm.blog.entity.User;
import com.mm.blog.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**ot
 * @Auther: mm
 * @Date: 2018/8/26 16:45
 * @Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;


    @Test
    public void testSaveUser(){
        User user=new User();

        user.setEmail("qq222222222@qq.com");
        user.setPassword("123456");
        user.setLoginName("xiaofeixia");
        user.setCreateTime(new Date());
        userService.saveUser(user);
    }

    @Test
    public void testFindUser(){
        User user=new User();
        user= userService.findUserByEmailAndPassword("qq332222222@qq.com","123456");
    }
}
