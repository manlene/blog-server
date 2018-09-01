package com.mm.blog.controller.handler;

import com.mm.blog.command.UserLoginCommand;
import com.mm.blog.command.UserRegisterCommand;
import com.mm.blog.constants.BlogConstants;
import com.mm.blog.entity.User;
import com.mm.blog.response.APIResponse;
import com.mm.blog.response.ResponseStatus;
import com.mm.blog.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: mm
 * @Date: 2018/8/27 16:11
 * @Description:
 */
@Component
public class UserHandler {

    @Autowired
    private UserService userService;

    public APIResponse<UserLoginCommand> userLogin(HttpServletRequest request, String email, String password) {
        //参数校验
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password))
            return APIResponse.build(ResponseStatus.FAIL_PARAMETER_EXCEPTION, null);
        User user = userService.findUserByEmailAndPassword(email, password);
        //登录失败
        if (StringUtils.isEmpty(user))
            return APIResponse.build(ResponseStatus.EMAIL_OR_PASSWORD_ERROR, null);
        //登录成功
        UserLoginCommand userLoginCommand = new UserLoginCommand();
        BeanUtils.copyProperties(user, userLoginCommand);
        //将登录的信息储存在userDetails
        request.getSession().setAttribute(BlogConstants.USER_DETAILS_SESSION, userLoginCommand);
        return APIResponse.build(ResponseStatus.LOGIN_SUCCESS, userLoginCommand);
    }

    public APIResponse saveUser(UserRegisterCommand userRegisterCommand) {
        //参数校验
        if (StringUtils.isEmpty(userRegisterCommand.getEmail()) || StringUtils.isEmpty(userRegisterCommand.getLoginName()) || StringUtils.isEmpty(userRegisterCommand.getPassword()) || StringUtils.isEmpty(userRegisterCommand.getRepassword()))
            return APIResponse.build(ResponseStatus.FAIL_PARAMETER_EXCEPTION, null);
        if (!userRegisterCommand.getPassword().equals(userRegisterCommand.getRepassword()))
            return APIResponse.build(ResponseStatus.PASSWORD_NOT_SAME_ERROR, null);
        User user = new User();
        BeanUtils.copyProperties(userRegisterCommand, user);
        user.setCreateTime(new Date());
        //默认是生效状态
        user.setStatus(User.STATUS_ACTIVE);
        userService.saveUser(user);

        return APIResponse.build(ResponseStatus.REGISTER_SUCCESS, null);
    }

    public APIResponse<List<UserLoginCommand>> findAllUser() {

        List<User> users=userService.findAllUser();
        List<UserLoginCommand> userLoginCommands=new ArrayList<UserLoginCommand>();
        for (User user:users) {
            UserLoginCommand userLoginCommand=new UserLoginCommand();
            BeanUtils.copyProperties(user,userLoginCommand);
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
            userLoginCommand.setCreateTimeStr(sf.format(userLoginCommand.getCreateTime()));
            userLoginCommands.add(userLoginCommand);
        }
        return APIResponse.build(ResponseStatus.FIND_USER_SUCCESS, userLoginCommands);
    }

    public  APIResponse<User> findUserById(String id){
        if (StringUtils.isEmpty(id))
            return APIResponse.build(ResponseStatus.FAIL_PARAMETER_EXCEPTION, null);
        User user=userService.findUserById(id);
       return APIResponse.build(ResponseStatus.FIND_USER_SUCCESS, user);
    }
    public APIResponse updateUser(UserRegisterCommand userRegisterCommand) {

        if (!userRegisterCommand.getPassword().equals(userRegisterCommand.getRepassword()))
            return APIResponse.build(ResponseStatus.PASSWORD_NOT_SAME_ERROR, null);
        User user = new User();
        BeanUtils.copyProperties(userRegisterCommand, user);

        userService.updateUser(user);

        return APIResponse.build(ResponseStatus.UPDATE_USER_SUCCESS, null);
    }
    public APIResponse updateUserStatus(UserRegisterCommand userRegisterCommand) {
        User user = new User();
        BeanUtils.copyProperties(userRegisterCommand, user);

        userService.updateUser(user);

        return APIResponse.build(ResponseStatus.UPDATE_USER_SUCCESS, null);
    }
}
