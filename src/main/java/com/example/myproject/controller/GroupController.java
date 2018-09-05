package com.example.myproject.controller;

import com.example.myproject.pojo.Group;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shop")
@Api(description = "群组模块")
public class GroupController {

    @Autowired
    private GroupService groupService;


    @RequestMapping(value = "/group/addGroup", method = RequestMethod.POST)
    @ApiOperation(value = "新增群组")
    public Result<Object> addNotice(@ModelAttribute Group group) {

        List<Group> groups = groupService.findGroupByNameOrName(group.getGroupId(), group.getGroupName());
        if (groups != null && groups.size() > 0) {
            return new ResultUtil<Object>().setErrorMsg("该群组已存在");
        }
        int result = groupService.addGroup(group);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("新增群组失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("新增群组成功");
    }


    @RequestMapping(value = "/group/findGroup", method = RequestMethod.POST)
    @ApiOperation(value = "查询群组")
    public Result<Object> findGroup(Long groupId, String groupName) {

        List<Group> groups = groupService.findGroupByNameOrName(groupId, groupName);
        return new ResultUtil<Object>().setData(groups);
    }

    @RequestMapping(value = "/group/findGroupDetails", method = RequestMethod.POST)
    @ApiOperation(value = "查询群组详细")
    public Result<Object> findGroupDetails(Long groupId, String groupName) {

        List<Group> groups = groupService.findGroupDetails(groupId, groupName);
        return new ResultUtil<Object>().setData(groups);
    }

    @RequestMapping(value = "/group/modifyGroup", method = RequestMethod.POST)
    @ApiOperation(value = "修改群组")
    public Result<Object> modifyGroup(@ModelAttribute Group group, @RequestParam Long groupId) {

        int result = groupService.modifyGroup(group, groupId);
        if (result == -1) {
            return new ResultUtil<Object>().setErrorMsg("修改群组失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("修改群组成功");
    }


    @RequestMapping(value = "/group/deleteGroup", method = RequestMethod.POST)
    @ApiOperation(value = "删除群组")
    public Result<Object> deleteGroup(Long groupId) {

        int result = groupService.deleteGroup(groupId);
        if (result == -1) {
            return new ResultUtil<Object>().setErrorMsg("删除群组失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("删除群组成功");
    }

}
