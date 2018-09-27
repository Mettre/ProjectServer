package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.example.myproject.menu.EnumBean;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.example.myproject.utils.SmsDemo.sendSms;

@RestController
@RequestMapping("/api/controller")
@Api(description = "验证码")
public class CaptchaController {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaController.class);

    @Autowired
    public UserService loginService;

    @Autowired
    private RedisService redisService;

    @RequestMapping(value = "/captcha", method = RequestMethod.POST)
    @ApiOperation(value = "生成验证码")
    public Result<Object> login(@RequestBody HashMap<String, Object> map) throws ClientException {

        String phone = (String) map.get("phone");
        String captchaCode = (String) map.get("captchaCode");

        if (StrUtil.isBlank(phone)) {
            return new ResultUtil<Object>().setErrorMsg("手机号不能为空");
        }
        Users users = loginService.findUserByPhone(phone);
        switch (EnumBean.CaptchaEnum.valueOf(captchaCode)) {
            case REGISTER_SMS:
                if (users != null) {
                    return new ResultUtil<Object>().setErrorMsg("该手机号已被注册");
                }
                break;
            case MODIFY_SMS:
                if (users == null) {
                    return new ResultUtil<Object>().setErrorMsg("该手机号尚未注册");
                }
                break;
        }
        String code = new CreateVerifyCode().randomInteger(4);
        Captcha captcha = new Captcha();
        captcha.setPhone(phone);
        captcha.setCode(code);
        captcha.setCaptchaType(captchaCode);
        //缓存验证码
        logger.info(captchaCode);
        switch (EnumBean.CaptchaEnum.valueOf(captchaCode)) {
            case REGISTER_SMS:
                redisService.set(AssembleUtils.registerUtils(phone), code);
                redisService.expire(AssembleUtils.registerUtils(phone), 3 * 60);
                break;
            case MODIFY_SMS:
                redisService.set(AssembleUtils.forgetUtils(phone), code);
                redisService.expire(AssembleUtils.forgetUtils(phone), 3 * 60);
                break;
        }
        SendSmsResponse response = sendSms(EnumBean.CaptchaEnum.valueOf(captchaCode), captcha);
        if ("OK".equals(response.getCode()) && "OK".equals(response.getMessage())) {
            return new ResultUtil<Object>().setData(captcha);
        } else {
            return new ResultUtil<Object>().setErrorMsg(response.getMessage());
        }
    }
}
