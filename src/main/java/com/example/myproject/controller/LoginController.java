package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.pojo.Users;
import com.example.myproject.service.LoginService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Api(description = "用户模块")
public class LoginController {

    @Autowired
    public LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public Result<Object> login(@RequestParam String phone, @RequestParam String password) {
        Users user = loginService.login(phone, password);
        if (user == null) {
            return new ResultUtil<Object>().setErrorMsg("账号或者密码错误");
        }
        return new ResultUtil<Object>().setData(user);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public Result<Object> insert(@RequestParam String name, @RequestParam String password, @RequestParam String phone, @RequestParam int sex, @RequestParam int type, @RequestParam int age) {

        if (StrUtil.isBlank(phone)) {
            return new ResultUtil<Object>().setErrorMsg("手机号不能为空");
        }
        Users users = loginService.selectUser(phone);
        if (users != null) {
            return new ResultUtil<Object>().setErrorMsg("该手机号已被注册");
        }
        Users user = new Users(name, phone, password, age, sex);
        int insertResult = loginService.insert(user);
        if (insertResult == -1) {
            return new ResultUtil<Object>().setSuccessMsg("注册失败");
        }
        return new ResultUtil<Object>().setErrorMsg("注册成功");
    }

}
