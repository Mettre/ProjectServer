package com.example.myproject.utils;

public enum ResultEnum {

    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    PRIMARY_SCHOOL(100, "我猜测,你可能还在上小学"),
    MIDDLE_SCHOOL(101, "你可能在上初中");


    private Integer code;
    private String msg;


    ResultEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    //枚举里面只要给get方法就可以了,因为枚举的使用都是直接用构造方法来创建,不会再从新set
    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
