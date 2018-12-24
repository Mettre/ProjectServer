package com.example.myproject.vm;

import com.example.myproject.menu.EnumBean;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class CaptchaVM {

    @NotBlank(message = "请输入账号")
    private String phone;

    @NotBlank(message = "请选择验证码类型")
    private EnumBean.CaptchaEnum captchaCode;
}
