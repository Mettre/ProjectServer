package com.example.myproject.vm;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class LoginVM {

    @NotBlank(message = "请输入账号")
    private String phone;

    @NotBlank(message = "请输入密码")
    private String password;
}
