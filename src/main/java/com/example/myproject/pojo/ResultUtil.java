package com.example.myproject.pojo;


import com.example.myproject.exception.ErrorCode;
import com.example.myproject.menu.ResultEnum;

/**
 * @author Exrick
 */
public class ResultUtil<T> {

    private Result<T> result;

    public ResultUtil() {
        result = new Result<>();
        result.setSuccess(true);
        result.setMessage("success");
        result.setCode(ErrorCode.UNKNOW);
    }

    public Result<T> setData(T t) {
        this.result.setData(t);
        this.result.setCode(ErrorCode.UNKNOW);
        return this.result;
    }

    public Result<T> setSuccessMsg(String msg) {
        this.result.setSuccess(true);
        this.result.setMessage(msg);
        this.result.setCode(ErrorCode.UNKNOW);
        this.result.setData(null);
        return this.result;
    }

    public Result<T> setData(T t, String msg) {
        this.result.setData(t);
        this.result.setCode(ErrorCode.UNKNOW);
        this.result.setMessage(msg);
        return this.result;
    }

    public Result<T> setErrorMsg(String msg) {
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(ErrorCode.UNKNOW);
        return this.result;
    }

    public Result<T> setErrorResultEnum(ResultEnum resultEnum) {
        this.result.setSuccess(false);
        this.result.setMessage(resultEnum.getMsg());
        this.result.setCode(resultEnum.getCode());
        return this.result;
    }

    public Result<T> setNotLoggedInMsg() {
        this.result.setSuccess(false);
        this.result.setMessage("未登录");
        this.result.setCode("401");
        return this.result;
    }

    public Result<T> setAuthenticationFailureMsg() {
        this.result.setSuccess(false);
        this.result.setMessage("登录失效");
        this.result.setCode("401");
        return this.result;
    }

    public Result<T> setErrorMsg(String code, String msg) {
        this.result.setSuccess(false);
        this.result.setMessage(msg);
        this.result.setCode(code);
        return this.result;
    }
}
