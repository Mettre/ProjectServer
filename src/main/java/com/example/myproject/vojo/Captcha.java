package com.example.myproject.vojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Exrickx
 */
@Data
public class Captcha implements Serializable {

    @ApiModelProperty(value = "验证码id")
    private String captchaId;

    @ApiModelProperty(value = "验证码")
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }
}
