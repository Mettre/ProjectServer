package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.constant.CommonConstant;
import com.example.myproject.jwt.AccessToken;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.pojo.Users;
import com.example.myproject.service.UserService;
import com.example.myproject.utils.AssembleUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/api/user")
@Api(description = "用户模块")
public class UserController {

    @Value("${ProjectService.tokenExpireTime}")
    private Integer tokenExpireTime;

    @Value("${ProjectService.saveLoginTime}")
    private Integer saveLoginTime;

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

        Long tokenExpiration = System.currentTimeMillis() + tokenExpireTime * 60 * 1000;

        //登陆成功生成token
        String token = Jwts.builder()
                //主题 放入用户名
                .setSubject(user.getId())
                //失效时间
                .setExpiration(new Date(tokenExpiration))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS256, CommonConstant.JWT_TOKEN)
                .compact();

        AccessToken accessToken = new AccessToken(token, token, tokenExpireTime);
        return new ResultUtil<Object>().setData(accessToken);

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public Result<Object> insert(@RequestParam String phone, @RequestParam String password, @RequestParam String captchaCode) {

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

        Users users = loginService.findUserByPhone(phone);
        if (users != null) {
            return new ResultUtil<Object>().setErrorMsg("该手机号已被注册");
        }
        Users user = new Users("", phone, password, 22, 1);
        int insertResult = loginService.insert(user);
        if (insertResult == -1) {
            return new ResultUtil<Object>().setSuccessMsg("注册失败");
        }
        return new ResultUtil<Object>().setErrorMsg("注册成功");
    }

    @RequestMapping(value = "/loginEd/modifyPassword", method = RequestMethod.POST)
    @ApiOperation(value = "修改密码")
    public Result<Object> modifyPassword(HttpServletRequest request, @RequestParam String newPassword, @RequestParam String oldPassword) {

        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        Users user = loginService.findUserByUserId(userId);
        if (user == null) {
            return new ResultUtil<Object>().setErrorMsg("用户为空");
        }
        if (StrUtil.isBlank(oldPassword)) {
            return new ResultUtil<Object>().setErrorMsg("老密码不能为空");
        }
        if (StrUtil.isBlank(newPassword)) {
            return new ResultUtil<Object>().setErrorMsg("新密码不能为空");
        }

        if (!StrUtil.equals(oldPassword, user.getPassword())) {
            return new ResultUtil<Object>().setErrorMsg("旧密码错误");
        }

        int completeResult = loginService.modifyPassword(oldPassword, newPassword);
        if (completeResult == -1) {
            return new ResultUtil<Object>().setErrorMsg("修改密码失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("修改密码成功");
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    @ApiOperation(value = "忘记密码")
    public Result<Object> forgetPassword(@RequestParam String phone, @RequestParam String captchaCode, @RequestParam String password) {

        if (StrUtil.isBlank(phone)) {
            return new ResultUtil<Object>().setErrorMsg("手机号不能为空");
        }

        String code = redisTemplate.opsForValue().get(AssembleUtils.forgetUtils(phone));
        if (StrUtil.isBlank(code)) {
            return new ResultUtil<Object>().setErrorMsg("验证码已过期，请重新获取");
        }
        if (!captchaCode.toLowerCase().equals(code.toLowerCase())) {
            log.error("注册失败，验证码错误：code:" + captchaCode + ",redisCode:" + code.toLowerCase());
            return new ResultUtil<Object>().setErrorMsg("验证码输入错误");
        }
        int completeResult = loginService.forgetPassword(phone, password);
        if (completeResult == -1) {
            return new ResultUtil<Object>().setErrorMsg("修改密码失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("修改密码成功");
    }

    @RequestMapping(value = "/loginEd/getUserId", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息")
    public Result<Object> getUserId(HttpServletRequest request) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        Users user = loginService.findUserByUserId(userId);
        if (user == null) {
            return new ResultUtil<Object>().setErrorMsg("用户为空");
        }
        return new ResultUtil<Object>().setData(user);
    }
}
