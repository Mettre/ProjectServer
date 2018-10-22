package com.example.myproject.controller;

import com.example.myproject.exception.CustomerException;
import com.example.myproject.pojo.*;
import com.example.myproject.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
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
    public Result<Object> addGoods(@RequestParam(value = "goodsName") String goodsName
            , @RequestParam(value = "categoryId") Integer categoryId
            , @RequestParam(value = "goodsSn") Long goodsSn
            , @RequestParam(value = "brandId") Integer brandId
            , @RequestParam(value = "stock") Integer stock
            , @RequestParam(value = "marketPrice") BigDecimal marketPrice
            , @RequestParam(value = "shopPrice") BigDecimal shopPrice
            , @RequestParam(value = "promotePrice", required = false) BigDecimal promotePrice
            , @RequestParam(value = "promoteStartDate", required = false) Date promoteStartDate
            , @RequestParam(value = "promoteEndDate", required = false) Date promoteEndDate
            , @RequestParam(value = "keywords", required = false) String keywords
            , @RequestParam(value = "goodsBrief", required = false) String goodsBrief
            , @RequestParam(value = "goodsDesc", required = false) String goodsDesc
            , @RequestParam(value = "sellerNote", required = false) String sellerNote) {

        Goods goods = new Goods();
        goods.setGoodsName(goodsName);
        goods.setCategoryId(categoryId);
        goods.setGoodsSn(goodsSn);
        goods.setBrandId(brandId);
        goods.setStock(stock);
        goods.setMarketPrice(marketPrice);
        goods.setShopPrice(shopPrice);
        goods.setPromotePrice(promotePrice);
        goods.setIsPromote(promotePrice != null);
        goods.setPromoteStartDate(promoteStartDate);
        goods.setPromoteEndDate(promoteEndDate);
        goods.setKeywords(keywords);
        goods.setGoodsBrief(goodsBrief);
        goods.setGoodsDesc(goodsDesc);
        goods.setSellerNote(sellerNote);

        int result = goodsService.addGoods(goods);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("添加商品失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("添加商品成功");
    }

    @RequestMapping(value = "/goods/modifyGoods", method = RequestMethod.POST)
    @ApiOperation(value = "修改商品")
    public Result<Object> modifyGoods(@ModelAttribute Goods goods) {
        Goods goods2 = new Goods(goods.getGoodsId());
        List<Goods> categoryList3 = goodsService.findGoods(goods2, 1, 0);
        if (categoryList3 == null && categoryList3.size() == 0) {
            return new ResultUtil<Object>().setErrorMsg("该商品不存在");
        }

        int result = goodsService.modifyGoods(goods);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("修改商品失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("修改商品成功");
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
    @ApiOperation(value = "查找商品列表")
    public Result<Object> findGoods(@ModelAttribute Goods goods
            , @RequestParam(value = "page", defaultValue = "1", required = false) int page
            , @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        int limit = size;
        int offset = 0;
        if (page > 1) {
            offset = size * (page - 1) - 1;
        } else {
            offset = 0;
        }
        List<Goods> goodsList = goodsService.findGoods(goods, limit, offset);

        return new ResultUtil<Object>().setData(goodsList);
    }

    @RequestMapping(value = "/goods/findGoodDetails", method = RequestMethod.POST)
    @ApiOperation(value = "查找商品详情")
    public Result<Object> findGoodDetails(@RequestParam(value = "goodsId") long goodsId) {
        return new ResultUtil<Object>().setData(goodsService.findGoodDetails(goodsId));
    }

    @RequestMapping(value = "/goods/promotionGoods", method = RequestMethod.POST)
    @ApiOperation(value = "查找促销商品")
    public Result<Object> promotionGoods(@RequestParam(value = "page", defaultValue = "1", required = false) int page
            , @RequestParam(value = "size", defaultValue = "10", required = false) int size) {

        int limit = size;
        int offset = 0;
        if (page > 1) {
            offset = size * (page - 1) - 1;
        } else {
            offset = 0;
        }
        List<Goods> goodsList = goodsService.promotionGoods(limit, offset);

        return new ResultUtil<Object>().setData(goodsList);
    }

}
