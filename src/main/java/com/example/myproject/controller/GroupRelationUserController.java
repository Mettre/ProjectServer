package com.example.myproject.controller;

import com.example.myproject.pojo.*;
import com.example.myproject.service.GroupRelationUserService;
import com.example.myproject.service.GroupService;
import com.example.myproject.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shop")
@Api(description = "群组成员关系表")
public class GroupRelationUserController {

    @Autowired
    private GroupRelationUserService groupRelationUserService;

    @Autowired
    private GroupService groupService;

    @Autowired
    public UserService userService;

    @RequestMapping(value = "/groupRelation/addGroupRelation", method = RequestMethod.POST)
    @ApiOperation(value = "新增群组成员关系")
    public Result<Object> addGroupRelation(@RequestParam(value = "groupId") Long groupId, @RequestParam(value = "userId") String userId) {

        List<Group> groups = groupService.findGroupByNameOrName(groupId, null);
        if (groups == null || groups.size() == 0) {
            return new ResultUtil<Object>().setErrorMsg("群组不存在");
        }
        Users user = userService.findUserByUserId(userId);
        if (user == null) {
            return new ResultUtil<Object>().setErrorMsg("用户不存在");
        }
        int result = groupRelationUserService.addGroupRelation(groupId, userId);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("群组成员关系绑定失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("群组成员关系绑定成功");
    }

    @RequestMapping(value = "/group/deleteGroupRelation", method = RequestMethod.POST)
    @ApiOperation(value = "删除群组关系")
    public Result<Object> deleteGroup(Long relationId) {

        int result = groupRelationUserService.deleteGroupRelation(relationId);
        if (result == -1) {
            return new ResultUtil<Object>().setErrorMsg("删除群组失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("新增群组成功");
    }


}
