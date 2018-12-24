package com.example.myproject.vm;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class RegisterVM {

    @NotBlank(message = "请输入账号")
    private String phone;

    @NotBlank(message = "请输入密码")
    private String password;

    @NotBlank(message = "请输入验证码")
    private String captchaCode;
}
