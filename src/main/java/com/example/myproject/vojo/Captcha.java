package com.example.myproject.vojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Exrickx
 */

@Data
public class Captcha implements Serializable {

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "验证码类型")
    private int captchaType;

    @ApiModelProperty(value = "验证码")
    private String code;
}
