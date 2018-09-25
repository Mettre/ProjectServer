package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.pojo.Users;
import com.example.myproject.redis.RedisService;
import com.example.myproject.service.UserService;
import com.example.myproject.utils.AssembleUtils;
import com.example.myproject.utils.CreateVerifyCode;
import com.example.myproject.vojo.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/controller")
@Api(description = "基础配置")
public class CaptchaController {


    @Autowired
    public UserService loginService;

    @Autowired
    private RedisService redisService;


    @RequestMapping(value = "/captcha", method = RequestMethod.POST)
    @ApiOperation(value = "生成验证码")
    public Result<Object> login(@RequestBody HashMap<String, Object> map) {

        String phone = (String) map.get("phone");
        int captchaType = (int) map.get("captchaType");

        if (StrUtil.isBlank(phone)) {
            return new ResultUtil<Object>().setErrorMsg("手机号不能为空");
        }
        Users users = loginService.findUserByPhone(phone);
        switch (captchaType) {
            case 1:
                if (users != null) {
                    return new ResultUtil<Object>().setErrorMsg("该手机号已被注册");
                }
                break;
            case 2:
                if (users == null) {
                    return new ResultUtil<Object>().setErrorMsg("该手机号尚未注册");
                }
                break;
        }
        String code = new CreateVerifyCode().randomInteger(4);
        Captcha captcha = new Captcha();
        captcha.setPhone(phone);
        captcha.setCode(code);
        captcha.setCaptchaType(captchaType);
        //缓存验证码
        switch (captchaType) {
            case 1:
                redisService.set(AssembleUtils.registerUtils(phone), code);
                redisService.expire(AssembleUtils.registerUtils(phone), 3 * 60);
                break;
            case 2:
                redisService.set(AssembleUtils.forgetUtils(phone), code);
                redisService.expire(AssembleUtils.forgetUtils(phone), 3 * 60);
                break;
        }
        return new ResultUtil<Object>().setData(captcha);
    }
}
