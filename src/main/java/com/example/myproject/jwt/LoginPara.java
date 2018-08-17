package com.example.myproject.jwt;

import lombok.Data;


/**
 * 添加登录获取token时，所需要的认证信息类LoginPara.Java
 */

@Data
public class LoginPara {

    private String clientId;
    private String userName;
    private String password;
}
