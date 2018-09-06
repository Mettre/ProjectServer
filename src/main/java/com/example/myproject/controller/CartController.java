package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.pojo.*;
import com.example.myproject.service.CartService;
import com.example.myproject.service.GoodsService;
import com.example.myproject.service.UserService;
import com.example.myproject.utils.UserUtils;
import com.example.myproject.vojo.CartBean;
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

    @Autowired
    public GoodsService goodsService;

    @Autowired
    public UserService loginService;

    @RequestMapping(value = "/cart/addCart", method = RequestMethod.POST)
    @ApiOperation(value = "新增购物车")
    public Result<Object> addCart(HttpServletRequest request, @ModelAttribute Cart cart) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = "0";
        if (claims != null) {//登录
            userId = claims.getSubject();
        }

        cart.setCartNumber(1);
        cart.setUserId(Long.parseLong(userId));
        if (cart.getUserId() <= 0 && cart.getSessionId() <= 0) {
            return new ResultUtil<Object>().setErrorMsg("参数错误");
        }
        if (cart.getCartNumber() <= 0) {
            return new ResultUtil<Object>().setErrorMsg("请选择商品数量");
        }
        if(cart.getUserId()>0){
            Users user = loginService.findUserByUserId(userId);
            if (user==null) {
                return new ResultUtil<Object>().setErrorMsg("用户不存在");
            }
        }else if(cart.getSessionId()<=0){
            return new ResultUtil<Object>().setErrorMsg("设备号不能为空");
        }

        Goods goods = goodsService.findGoodDetails(cart.getGoodsId());
        if (goods == null) {
            return new ResultUtil<Object>().setErrorMsg("商品不存在");
        }
        cart.setCartPrice(goods.getShopPrice());
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


    @RequestMapping(value = "/cart/findAllCart", method = RequestMethod.POST)
    @ApiOperation(value = "查询购物车")
    public Result<Object> findAllCart(HttpServletRequest request, Long sessionId) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = "0";
        if (claims != null) {//登录
            userId = claims.getSubject();
        }

        if(!"0".equals(userId)){
            Users user = loginService.findUserByUserId(userId);
            if (user==null) {
                return new ResultUtil<Object>().setErrorMsg("用户不存在");
            }
        }else if(sessionId<=0){
            return new ResultUtil<Object>().setErrorMsg("设备号不能为空");
        }

        CartBean cartBean = cartService.findAllCart(Long.parseLong(userId), sessionId);
        return new ResultUtil<Object>().setData(cartBean);
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
    public Result<Object> deleteAllCart(HttpServletRequest request, Long sessionId) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = "0";
        if (claims != null) {//登录
            userId = claims.getSubject();
        }
        int result = cartService.deleteAllCart(Long.parseLong(userId), sessionId);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("删除失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("删除成功");
    }
}
