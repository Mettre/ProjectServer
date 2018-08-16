package com.example.myproject.menu;

import com.example.myproject.constant.CommonConstant;

public class MettreMenu {

    public static String getSexStr(int sex) {
        return sex == CommonConstant.SEX_MAN ? "男" : "女";
    }

    public static int getSexInterger(String sex) {
        return "男".equals(sex) ? CommonConstant.SEX_WOMAN : CommonConstant.SEX_MAN;
    }
}
