package com.example.myproject.controller;


import com.example.myproject.constant.CommonConstant;
import com.example.myproject.pojo.Group;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.service.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shop")
@Api(description = "分组模块")
public class GroupController {

    @Autowired
    private GroupService userGroupService;


    @RequestMapping(value = "/group/addGroup", method = RequestMethod.POST)
    @ApiOperation(value = "新增分组")
    public Result<Object> addNotice(@ModelAttribute Group group) {

        List<Group> groups = userGroupService.findGroupByNameOrName(group.getGroupId(), group.getGroupName());
        if (groups != null && groups.size() > 0) {
            return new ResultUtil<Object>().setErrorMsg("该分组已存在");
        }
        int result = userGroupService.addGroup(group);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("新增分组失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("新增分组成功");
    }


    @RequestMapping(value = "/group/findGroup", method = RequestMethod.POST)
    @ApiOperation(value = "查询分组")
    public Result<Object> findGroup(Long groupId, String groupName) {

        List<Group> groups = userGroupService.findGroupByNameOrName(groupId, groupName);
        return new ResultUtil<Object>().setData(groups);
    }


    @RequestMapping(value = "/group/deleteGroup", method = RequestMethod.POST)
    @ApiOperation(value = "删除分组")
    public Result<Object> deleteGroup(Long groupId) {

        int result = userGroupService.deleteGroup(groupId);
        if (result == -1) {
            return new ResultUtil<Object>().setErrorMsg("删除分组失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("新增分组成功");
    }

}
