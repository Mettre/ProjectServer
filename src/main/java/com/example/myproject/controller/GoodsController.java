package com.example.myproject.controller;

import com.example.myproject.pojo.*;
import com.example.myproject.service.GoodsService;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/shop")
@Api(description = "商品相关")
public class GoodsController {

    @Autowired
    public GoodsService goodsService;

    @RequestMapping(value = "/goods/addGoods", method = RequestMethod.POST)
    @ApiOperation(value = "新增商品")
    public Result<Object> addGoods(@ModelAttribute Goods goods) {

        List<Goods> categoryList3 = goodsService.findGoods(goods);
        if (categoryList3 != null && categoryList3.size() > 0) {
            return new ResultUtil<Object>().setErrorMsg("该商品id已存在");
        }

        int result = goodsService.addGoods(goods);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("添加商品失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("添加商品成功");
    }


    @RequestMapping(value = "/goods/deleteGoods", method = RequestMethod.POST)
    @ApiOperation(value = "删除商品")
    public Result<Object> deleteDelivery(@RequestParam String goodsIds) {
        String ids[] = goodsIds.split(",");
        int status = 0;
        if (ids != null && ids.length > 0) {
            for (String goods_id : ids) {
                status = goodsService.deleteGoods(Integer.parseInt(goods_id));
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
                return new ResultUtil<Object>().setErrorMsg("删除商品失败");
            }
        } else {
            return new ResultUtil<Object>().setErrorMsg("入参不正确");
        }
    }

    @RequestMapping(value = "/goods/findGoods", method = RequestMethod.POST)
    @ApiOperation(value = "查找商品")
    public Result<Object> findBrand(@ModelAttribute Goods goods) {
        List<Goods> categoryList = goodsService.findGoods(goods);
        return new ResultUtil<Object>().setData(categoryList);
    }

}
