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
import java.util.List;

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

    @RequestMapping(value = "/loginEd/updateAddress", method = RequestMethod.POST)
    @ApiOperation(value = "修改收货地址")
    public Result<Object> updateDelivery(HttpServletRequest request, @ModelAttribute Address address) {

        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        address.setUserId(userId);
        int insertResult = addressService.update(address);
        if (insertResult == -1) {
            return new ResultUtil<Object>().setErrorMsg("修改收货地址失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("修改地址成功");
    }

    @RequestMapping(value = "/loginEd/findByPage", method = RequestMethod.POST)
    @ApiOperation(value = "分页查找收货地址")
    public Result<Object> findByPage(HttpServletRequest request, @RequestParam Integer page, @RequestParam Integer size) {

        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        int limit = size;
        int offset = 0;
        if (page > 1) {
            offset = size * (page - 1) - 1;
        } else {
            offset = 0;
        }
        List<Address> addressList = addressService.findByPage(userId, limit, offset);
        return new ResultUtil<Object>().setData(addressList);
    }

    @RequestMapping(value = "/loginEd/deleteDelivery", method = RequestMethod.POST)
    @ApiOperation(value = "删除收货地址")
    public Result<Object> deleteDelivery(HttpServletRequest request, @RequestParam String addressIds) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        String ids[] = addressIds.split(",");
        int status = 0;
        for (String addressId : ids) {
            status = addressService.deleteAddress(userId, addressId);
            if (status == 0 || status == -1) {
                break;
            }
        }
        if (status != -1) {
            if (status != 0) {
                return new ResultUtil<Object>().setSuccessMsg("删除成功");
            } else {
                return new ResultUtil<Object>().setErrorMsg("有不存在的id,删除失败");
            }
        } else {
            return new ResultUtil<Object>().setErrorMsg("删除失败");
        }
    }
}
