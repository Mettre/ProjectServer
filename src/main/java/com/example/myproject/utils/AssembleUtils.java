package com.example.myproject.utils;

import com.example.myproject.constant.CommonConstant;

/**
 * 组装拼接字符串
 */
public class AssembleUtils {

    /**
     * 注册
     *
     * @param phone
     * @return
     */
    public static String registerUtils(String phone) {
        return phone + "-" + CommonConstant.REGISTER_CAPTCHA;
    }
}
