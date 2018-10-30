package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.exception.CustomerException;
import com.example.myproject.jwt.AccessToken;
import com.example.myproject.menu.ResultEnum;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.pojo.Users;
import com.example.myproject.redis.RedisService;
import com.example.myproject.service.UserService;
import com.example.myproject.utils.AssembleUtils;
import com.example.myproject.utils.BigDecimalUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;

@Slf4j
@RestController
@RequestMapping("/api/user")
@Api(description = "用户模块")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Value("${ProjectService.tokenExpireTime}")
    private Long tokenExpireTime;

    @Value("${ProjectService.saveLoginTime}")
    private Integer saveLoginTime;

    @Autowired
    private RedisService redisService;

    @Autowired
    public UserService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "用户登录")
    public Result<Object> login(@RequestBody HashMap<String, Object> map) {

        String phone = (String) map.get("phone");
        String password = (String) map.get("password");

        Users user = loginService.findUserByPhone(phone);
        if (user == null) {
            return new ResultUtil<Object>().setErrorMsg("账号未注册");
        }
        if (!password.equals(user.getPassword())) {
            return new ResultUtil<Object>().setErrorMsg("密码不正确");
        }
        Long tokenExpiration = System.currentTimeMillis() + tokenExpireTime * 60 * 1000;
        SecretKey key = BigDecimalUtils.generalKey();//生成签名的时候使用的秘钥secret,这个方法本地封装了的，一般可以从本地配置文件中读取，切记这个秘钥不能外露哦。它就是你服务端的私钥，在任何场景都不应该流露出去。一旦客户端得知这个secret, 那就意味着客户端是可以自我签发jwt了。
        //登陆成功生成token
        String jwt = Jwts.builder()
                //主题 放入用户名
                .setSubject(String.valueOf(user.getId()))
                //失效时间
                .setExpiration(new Date(tokenExpiration))
                //签名算法和密钥
                .signWith(SignatureAlgorithm.HS256, key)
                .compact();

        AccessToken accessToken = new AccessToken(jwt, "basic", tokenExpireTime);
        return new ResultUtil<Object>().setData(accessToken);

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ApiOperation(value = "用户注册")
    public Result<Object> insert(@RequestBody HashMap<String, Object> map) {

        String phone = (String) map.get("phone");
        String password = (String) map.get("password");
        String captchaCode = (String) map.get("captchaCode");

        if (StrUtil.isBlank(phone)) {
            return new ResultUtil<Object>().setErrorMsg("手机号不能为空");
        }

        String code = redisService.get(AssembleUtils.registerUtils(phone));
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
        Users user = new Users("", phone, password.trim(), 22, 1);
        int insertResult = loginService.insert(user);
        if (insertResult == -1) {
            return new ResultUtil<Object>().setSuccessMsg("注册失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("注册成功");
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

        if (!oldPassword.equals(user.getPassword())) {
            return new ResultUtil<Object>().setErrorMsg("旧密码错误");
        }

        int completeResult = loginService.modifyPassword(Long.parseLong(userId), newPassword);
        if (completeResult == -1) {
            return new ResultUtil<Object>().setErrorMsg("修改密码失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("修改密码成功");
    }

    @RequestMapping(value = "/forgetPassword", method = RequestMethod.POST)
    @ApiOperation(value = "忘记密码")
    public Result<Object> forgetPassword(@RequestBody HashMap<String, Object> map) {

        String phone = (String) map.get("phone");
        String captchaCode = (String) map.get("captchaCode");
        String password = (String) map.get("password");

        if (StrUtil.isBlank(phone)) {
            throw new CustomerException("手机号不能为空");
        }

        String code = redisService.get(AssembleUtils.forgetUtils(phone));
        if (StrUtil.isBlank(code)) {
            throw new CustomerException(ResultEnum.VERIFICATION_EXPIRES);
        }
        if (!captchaCode.toLowerCase().equals(code.toLowerCase())) {
            throw new CustomerException(ResultEnum.VERIFICATION_ERROR);
        }
        int completeResult = loginService.forgetPassword(phone, password.trim());
        if (completeResult == -1) {
            throw new CustomerException(ResultEnum.PASSWORD_MODIFICATION_FAILED);
        }
        return new ResultUtil<Object>().setSuccessMsg("修改密码成功");
    }

    @RequestMapping(value = "/loginEd/getUserInfo", method = RequestMethod.GET)
    @ApiOperation(value = "获取用户信息")
    public Result<Object> getUserId(HttpServletRequest request) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        Users user = loginService.findUserByUserId(userId);
        user.setPassword("");
        if (user == null) {
            return new ResultUtil<Object>().setErrorMsg("用户为空");
        }
        return new ResultUtil<Object>().setData(user);
    }


    @RequestMapping(value = "/loginEd/editUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户信息")
    public Result<Object> editUserInfo(HttpServletRequest request, @RequestBody Users users) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        if (StrUtil.isBlank(userId)) {
            return new ResultUtil<Object>().setErrorMsg("用户为空");
        }
        users.setId(Long.parseLong(userId));
        int editResult = loginService.editUserInfo(users);
        if (editResult == -1) {
            return new ResultUtil<Object>().setErrorMsg("修改个人信息失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("修改成功");
    }
}
