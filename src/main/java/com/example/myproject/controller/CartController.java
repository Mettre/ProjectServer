package com.example.myproject.controller;

import com.example.myproject.pojo.*;
import com.example.myproject.service.CartService;
import com.example.myproject.service.GoodsService;
import com.example.myproject.service.UserService;
import com.example.myproject.vojo.CartBean;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

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

    @RequestMapping(value = "/loginEd/cart/addCart", method = RequestMethod.POST)
    @ApiOperation(value = "新增购物车")
    public Result<Object> addCart(HttpServletRequest request, Long sessionId, @RequestParam(value = "goodsId") Long goodsId, @RequestParam(value = "cartNumber") int cartNumber) {

        Cart cart = new Cart();
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = "0";
        if (claims != null) {//登录
            userId = claims.getSubject();
        }

        cart.setGoodsId(goodsId);
        cart.setSessionId(sessionId);
        cart.setCartNumber(cartNumber);
        cart.setUserId(Long.parseLong(userId));
        if (cart.getUserId() <= 0 && sessionId <= 0) {
            return new ResultUtil<Object>().setErrorMsg("参数错误");
        }
        if (cartNumber <= 0) {
            return new ResultUtil<Object>().setErrorMsg("请选择商品数量");
        }
        if (cart.getUserId() > 0) {
            Users user = loginService.findUserByUserId(userId);
            if (user == null) {
                return new ResultUtil<Object>().setErrorMsg("用户不存在");
            }
        } else if (sessionId <= 0) {
            return new ResultUtil<Object>().setErrorMsg("设备号不能为空");
        }

        Goods goods = goodsService.findGoodDetails(goodsId);
        if (goods == null) {
            return new ResultUtil<Object>().setErrorMsg("商品不存在");
        }
        cart.setCartPrice(goods.getShopPrice());
        int result;
        Cart cart2 = cartService.finCartByGoodsId(goodsId);
        if (cart2 == null) {
            result = cartService.addCart(cart);
        } else {
            result = cartService.editCartNum(Long.parseLong(userId), cart.getSessionId(), cart.getGoodsId(), cart2.getCartNumber() + 1);
        }
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("添加购物车失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("添加购物车成功");
    }

    @RequestMapping(value = "/cart/editCartNum", method = RequestMethod.POST)
    @ApiOperation(value = "编辑购物车数量")
    public Result<Object> editCartNum(HttpServletRequest request, @RequestParam Long goodsId, @RequestParam int cartNumber, Long sessionId) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = "0";
        if (claims != null) {//登录
            userId = claims.getSubject();
        }

        if (goodsId <= 0) {
            return new ResultUtil<Object>().setErrorMsg("商品不能为空");
        }
        if (cartNumber <= 0) {
            return new ResultUtil<Object>().setErrorMsg("数量不能小于0");
        }

        Goods goods = goodsService.findGoodDetails(goodsId);
        if (goods == null) {
            return new ResultUtil<Object>().setErrorMsg("商品不存在");
        }
        if (goods.getStock() < cartNumber) {
            return new ResultUtil<Object>().setErrorMsg("数量不能小于0");
        }

        int result = cartService.editCartNum(Long.parseLong(userId), sessionId, goodsId, cartNumber);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("购物车数量修改失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("购物车数量修改成功");
    }


    @RequestMapping(value = "/loginEd/cart/findAllCart", method = RequestMethod.POST)
    @ApiOperation(value = "查询购物车")
    public Result<Object> findAllCart(HttpServletRequest request, Long sessionId) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = "0";
        if (claims != null) {//登录
            userId = claims.getSubject();
        }

        if (!"0".equals(userId)) {
            Users user = loginService.findUserByUserId(userId);
            if (user == null) {
                return new ResultUtil<Object>().setErrorMsg("用户不存在");
            }
            int result = cartService.cartMerge(sessionId, Long.parseLong(userId));
            if (result < 0) {
                return new ResultUtil<Object>().setErrorMsg("购物车合并失败");
            }
        } else if (sessionId <= 0) {
            return new ResultUtil<Object>().setErrorMsg("设备号不能为空");
        }


        List<CartBean> cartBeans = cartService.findAllCart(Long.parseLong(userId), sessionId);
        return new ResultUtil<Object>().setData(cartBeans);
    }


    @RequestMapping(value = "/cart/deleteCart", method = RequestMethod.POST)
    @ApiOperation(value = "删除某个购物车项")
    public Result<Object> deleteCart(@RequestParam List<Long> cartIds, Long sessionId, HttpServletRequest request) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = "0";
        if (claims != null) {//登录
            userId = claims.getSubject();
        }
        if ("0".equals(userId) && (sessionId == null || sessionId <= 0)) {
            return new ResultUtil<Object>().setErrorMsg("请输入设备号或者登录");
        }
        int status = 0;
        for (Long cartId : cartIds) {
            status = cartService.deleteCart(cartId, Long.parseLong(userId), sessionId);
            if (status == 0 || status == -1) {
                break;
            }
        }
        if (status != -1) {
            if (status != 0) {
                return new ResultUtil<Object>().setSuccessMsg("删除购物车成功");
            } else {
                return new ResultUtil<Object>().setErrorMsg("有不存在的id,删除失败");
            }
        } else {
            return new ResultUtil<Object>().setErrorMsg("删除购物车失败");
        }
    }


    @RequestMapping(value = "/cart/deleteAllCart", method = RequestMethod.GET)
    @ApiOperation(value = "删除个人全部购物车")
    public Result<Object> deleteAllCart(HttpServletRequest request, Long sessionId) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = "0";
        if (claims != null) {//登录
            userId = claims.getSubject();
        }
        if ("0".equals(userId) && (sessionId == null || sessionId <= 0)) {
            return new ResultUtil<Object>().setErrorMsg("请输入设备号或者登录");
        }
        int result = cartService.deleteAllCart(Long.parseLong(userId), sessionId);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("删除失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("删除成功");
    }
}
