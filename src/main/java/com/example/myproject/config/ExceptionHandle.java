package com.example.myproject.config;

import com.example.myproject.exception.CustomerException;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 直接在右边的文件框里编辑你说需要注释的东西，
 * 然后应用保存之后,当你创建类的时候就会自动生成注释。
 */
@ControllerAdvice
public class ExceptionHandle {

    private final static Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)  //申明要捕获的异常类
    @ResponseBody
    public Result<Object> handle(Exception e) {
        if (e instanceof CustomerException) {
            CustomerException customerException = (CustomerException) e;
            return new ResultUtil<Object>().setErrorMsg(Integer.parseInt(customerException.getErrorCode()), e.getMessage());
        } else {
            logger.error("[系统异常 {}", e);
            return new ResultUtil<Object>().setErrorMsg("未知错误");
//            return new ResultUtil<Object>().setErrorMsg(e.getMessage());
        }
    }
}