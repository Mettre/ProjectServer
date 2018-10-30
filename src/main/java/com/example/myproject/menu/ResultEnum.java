package com.example.myproject.menu;

public enum ResultEnum {

    UNKONW_ERROR("-1", "未知错误"),
    SUCCESS("0", "成功"),
    PRIMARY_SCHOOL("100", "我猜测,你可能还在上小学"),
    MIDDLE_SCHOOL("101", "你可能在上初中"),
    IMG_NOT_EMPTY("102", "图片不存在"),
    IMG_QINIUYUN_EMPTY("103", "上传失败，请检查七牛云配置"),
    IMG_FORMAT_ERROR("104", "图片格式不正确"),
    PASSWORD_MODIFICATION_FAILED("105", "修改密码失败"),
    GOODS_NOT("106", "商品不存在"),
    VERIFICATION_ERROR("107", "验证码输入错误"),
    VERIFICATION_EXPIRES("109", "验证码已过期，请重新获取"),
    GOODS_ID_NOT_EMPTY("108", "商品id不能为空");

    private String code;
    private String msg;


    ResultEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
