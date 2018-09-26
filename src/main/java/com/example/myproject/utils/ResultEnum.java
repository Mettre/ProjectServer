package com.example.myproject.utils;

public enum ResultEnum {

    UNKONW_ERROR(-1, "未知错误"),
    SUCCESS(0, "成功"),
    PRIMARY_SCHOOL(100, "我猜测,你可能还在上小学"),
    MIDDLE_SCHOOL(101, "你可能在上初中"),
    IMG_NOT_EMPTY(102, "图片不存在"),
    IMG_QINIUYUN_EMPTY(103, "上传失败，请检查七牛云配置"),
    IMG_FORMAT_ERROR(104, "图片格式不正确");

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
