package com.example.myproject.controller;

import com.example.myproject.po.Result;
import com.example.myproject.po.ResultUtil;
import com.example.myproject.po.Users;
import com.example.myproject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    public LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Result<Object> login(@RequestParam String login, @RequestParam String password) {
        Users user = loginService.login(login, password);
        if (user == null) {
            return new ResultUtil<Object>().setErrorMsg("账号或者密码错误");
        }
        return new ResultUtil<Object>().setData(user);
    }

}
