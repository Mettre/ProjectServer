package com.example.myproject.controller;

import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.utils.CreateVerifyCode;
import com.example.myproject.vojo.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/controller")
@Api(description = "基础配置")
public class CaptchaController {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @RequestMapping(value = "/captcha", method = RequestMethod.POST)
    @ApiOperation(value = "生成验证码")
    public Result<Object> login(@RequestParam String phone) {
        String captchaId = UUID.randomUUID().toString().replace("-", "");
        String code = new CreateVerifyCode().randomStr(4);
        Captcha captcha = new Captcha();
        captcha.setCaptchaId(captchaId);
        //缓存验证码
        redisTemplate.opsForValue().set(captchaId, code, 3L, TimeUnit.MINUTES);
        return new ResultUtil<Object>().setData(captcha);
    }
}
