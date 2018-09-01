package com.mm.blog.controller;

import com.mm.blog.bind.LoginUser;
import com.mm.blog.bind.NeedLogin;
import com.mm.blog.command.TagCommand;
import com.mm.blog.command.UserLoginCommand;
import com.mm.blog.controller.handler.TagHandler;
import com.mm.blog.entity.Tag;
import com.mm.blog.response.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: mm
 * @Date: 2018/8/30 18:30
 * @Description:
 */
@RestController
public class TagController {

    @Autowired
    private TagHandler tagHandler;


    @PostMapping("/tag/save")
    public APIResponse saveTag(@RequestBody Tag tag, @LoginUser UserLoginCommand userLoginCommand) {
        tag.setUserId(userLoginCommand.getId());
        return tagHandler.saveTag(tag);
    }

    @GetMapping("/tag")
    public APIResponse<List<TagCommand>> findAllTagsByUserId(@LoginUser UserLoginCommand userLoginCommand) {
        return tagHandler.findAllTagsByUserId(userLoginCommand.getId(),null);
    }

    @PatchMapping("/tag/update")
    public APIResponse<List<TagCommand>> updateTag(@RequestBody  Tag tag) {
        return tagHandler.updateTagStatusById(tag);
    }

    @GetMapping("/tag/active")
    public APIResponse<List<TagCommand>> findActiveTagsByUserId(@LoginUser UserLoginCommand userLoginCommand) {
        return tagHandler.findAllTagsByUserId(userLoginCommand.getId(),Tag.STATUS_ACTIVE);
    }
}
