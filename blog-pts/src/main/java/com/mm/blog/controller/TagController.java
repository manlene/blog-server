package com.mm.blog.controller;

import com.mm.blog.bind.LoginUser;
import com.mm.blog.command.TagCommand;
import com.mm.blog.controller.handler.TagHandler;
import com.mm.blog.entity.Tag;
import com.mm.blog.entity.User;
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
    public APIResponse saveTag(@RequestBody Tag tag, @LoginUser User user) {
        tag.setUserId(user.getId());
        return tagHandler.saveTag(tag);
    }

    @GetMapping("/tag")
    public APIResponse<List<TagCommand>> findAllTagsByUserId(@LoginUser User user) {
        return tagHandler.findAllTagsByUserId(user.getId(),null);
    }

    @PatchMapping("/tag/update")
    public APIResponse<List<TagCommand>> updateTag(@RequestBody  Tag tag) {
        return tagHandler.updateTagStatusById(tag );
    }

    @GetMapping("/tag/active")
    public APIResponse<List<TagCommand>> findActiveTagsByUserId(@LoginUser User user) {
        return tagHandler.findAllTagsByUserId(user.getId(),Tag.STATUS_ACTIVE);
    }
}
