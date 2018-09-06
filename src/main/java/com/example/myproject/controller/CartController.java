package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.pojo.Cart;
import com.example.myproject.pojo.Category;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.service.CartService;
import com.example.myproject.utils.UserUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/shop")
@Api(description = "购物车")
public class CartController {

    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/cart/addCart", method = RequestMethod.POST)
    @ApiOperation(value = "新增购物车")
    public Result<Object> addCart(@ModelAttribute Cart cart) {

        if (cart.getUserId() <= 0 && cart.getSessionId() <= 0) {
            return new ResultUtil<Object>().setErrorMsg("参数错误");
        }
        if (cart.getCartNumber() <= 0) {
            return new ResultUtil<Object>().setErrorMsg("请选择商品数量");
        }
        if (!UserUtils.getInstance().hasUser(String.valueOf(cart.getUserId()))) {
            return new ResultUtil<Object>().setErrorMsg("用户不存在");
        }
        if (!UserUtils.getInstance().hasGoodS(cart.getGoodsId())) {
            return new ResultUtil<Object>().setErrorMsg("商品不存在");
        }
        int result;
        Cart cart2 = cartService.finCartByGoodsId(cart.getGoodsId());
        if (cart2 == null) {
            result = cartService.addCart(cart);
        } else {
            result = cartService.addCartNum(cart.getGoodsId(), cart2.getCartNumber() + 1);
        }
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("添加购物车失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("添加购物车成功");
    }

    @RequestMapping(value = "/cart/addCartNum", method = RequestMethod.POST)
    @ApiOperation(value = "购物车数量变化")
    public Result<Object> addCartNum(@RequestParam Long goodsId, @RequestParam int cartNumber) {

        if (goodsId <= 0) {
            return new ResultUtil<Object>().setErrorMsg("商品不能为空");
        }
        if (cartNumber <= 0) {
            return new ResultUtil<Object>().setErrorMsg("数量不能小于0");
        }
        int result = cartService.addCartNum(goodsId, cartNumber);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("购物车数量修改失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("购物车数量修改成功");
    }


    @RequestMapping(value = "/cart/deleteCart", method = RequestMethod.POST)
    @ApiOperation(value = "删除某个购物车项")
    public Result<Object> deleteCart(@RequestParam Long cartId) {

        if (cartId <= 0) {
            return new ResultUtil<Object>().setErrorMsg("参数异常");
        }
        int result = cartService.deleteCart(cartId);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("删除失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("删除成功");
    }


    @RequestMapping(value = "/cart/deleteAllCart", method = RequestMethod.GET)
    @ApiOperation(value = "删除个人全部购物车")
    public Result<Object> deleteAllCart(HttpServletRequest request) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        int result = cartService.deleteAllCart(Long.parseLong(userId));
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("删除失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("删除成功");
    }
}
