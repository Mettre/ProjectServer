package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.pojo.Users;
import com.example.myproject.service.UserService;
import com.example.myproject.utils.AssembleUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
@Api(description = "用户模块")
public class UserController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    public UserService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public Result<Object> login(@RequestParam String phone, @RequestParam String password) {
        Users user = loginService.login(phone, password);
        if (user == null) {
            return new ResultUtil<Object>().setErrorMsg("账号或者密码错误");
        }
        return new ResultUtil<Object>().setData(user);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public Result<Object> insert(@RequestParam String captchaCode, @RequestParam String name, @RequestParam String password, @RequestParam String phone, @RequestParam int sex, @RequestParam int type, @RequestParam int age) {

        if (StrUtil.isBlank(phone)) {
            return new ResultUtil<Object>().setErrorMsg("手机号不能为空");
        }

        String code = redisTemplate.opsForValue().get(AssembleUtils.registerUtils(phone));
        if (StrUtil.isBlank(code)) {
            return new ResultUtil<Object>().setErrorMsg("验证码已过期，请重新获取");
        }
        if (!captchaCode.toLowerCase().equals(code.toLowerCase())) {
            log.error("注册失败，验证码错误：code:" + captchaCode + ",redisCode:" + code.toLowerCase());
            return new ResultUtil<Object>().setErrorMsg("验证码输入错误");
        }

        Users users = loginService.findUser(phone);
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
