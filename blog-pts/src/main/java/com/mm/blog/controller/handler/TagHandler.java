package com.mm.blog.controller.handler;

import com.mm.blog.command.TagCommand;
import com.mm.blog.entity.Tag;
import com.mm.blog.response.APIResponse;
import com.mm.blog.response.ResponseStatus;
import com.mm.blog.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Auther: mm
 * @Date: 2018/8/30 18:30
 * @Description:
 */
@Component
public class TagHandler {

    @Autowired
    private TagService tagService;

    public APIResponse saveTag(Tag tag) {
        if (StringUtils.isEmpty(tag.getTagName()))
            return APIResponse.build(ResponseStatus.FAIL_PARAMETER_EXCEPTION, null);
        tag.setCreateTime(new Date());
        tag.setStatus(Tag.STATUS_ACTIVE);
        tagService.saveTag(tag);
        return APIResponse.build(ResponseStatus.SAVE_TAG_SUCCESS, null);
    }


    public APIResponse<List<TagCommand>> findAllTagsByUserId(String userId,Integer status) {

        List<Tag> tags = tagService.findTagsByUserId(userId,status);
        List<TagCommand> tagCommands = new ArrayList<TagCommand>();

        for (Tag tag : tags) {
            TagCommand tagCommand = new TagCommand();
            BeanUtils.copyProperties(tag, tagCommand);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            tagCommand.setCreateTimeStr(sdf.format(tag.getCreateTime()));
            tagCommands.add(tagCommand);
        }
        return APIResponse.build(ResponseStatus.FIND_TAGS_SUCCESS, tagCommands);
    }

    public APIResponse updateTagById(Tag tag) {
        if (StringUtils.isEmpty(tag.getTagName()))
            return APIResponse.build(ResponseStatus.FAIL_PARAMETER_EXCEPTION, null);

        tagService.updateTagById(tag);
        return APIResponse.build(ResponseStatus.UPDATE_TAG_SUCCESS, null);
    }

    public APIResponse updateTagStatusById(Tag tag) {
        if (StringUtils.isEmpty(tag.getStatus()))
            return APIResponse.build(ResponseStatus.FAIL_PARAMETER_EXCEPTION, null);

        tagService.updateTagById(tag);
        return APIResponse.build(ResponseStatus.UPDATE_TAG_SUCCESS, null);
    }
}
