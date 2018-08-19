package com.example.myproject.controller;


import com.example.myproject.pojo.Address;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.service.DeliveryService;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping("/api/delivery")
@Api(description = "收货地址")
public class DeliveryController {

    @Autowired
    public DeliveryService addressService;


    @RequestMapping(value = "/loginEd/insertAddress", method = RequestMethod.POST)
    @ApiOperation(value = "新增收货地址")
    public Result<Object> addDelivery(HttpServletRequest request, @ModelAttribute Address address) {

        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        address.setUserId(userId);
        int insertResult = addressService.insert(address);
        if (insertResult == -1) {
            return new ResultUtil<Object>().setErrorMsg("新增收货地址失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("新增地址成功");
    }
}
