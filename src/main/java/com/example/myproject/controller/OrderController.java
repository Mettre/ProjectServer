package com.example.myproject.controller;

import com.example.myproject.pojo.Order;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.service.GoodsService;
import com.example.myproject.service.OrderService;
import com.example.myproject.vojo.OrderRequestBean;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/shop")
@Api(description = "订单")
public class OrderController {

    @Autowired
    public GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/loginEd/order/addOrder", method = RequestMethod.POST)
    @ApiOperation(value = "新增订单")
    public Result<Object> addOrder(HttpServletRequest request, @RequestBody List<OrderRequestBean> orderBeanList) {

        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();

        if (orderBeanList == null || orderBeanList.size() == 0) {
            return new ResultUtil<Object>().setErrorMsg("参数不正确");
        }
        int result = 0;
        for (OrderRequestBean orderBean : orderBeanList) {
            if (orderBean.getGoodsItems() == null || orderBean.getGoodsItems().size() == 0) {
                break;
            } else {
                Order order = new Order();
                order.setUserId(Long.parseLong(userId));
                BigDecimal orderPrice = null;
                for (OrderRequestBean.GoodsItem goodsItem : orderBean.getGoodsItems()) {
                    BigDecimal goodsPrice = goodsService.findPrice(goodsItem.getGoodsId());
                    if (orderPrice == null) {
                        orderPrice = goodsPrice;
                    } else {
                        orderPrice = orderPrice.add(goodsPrice);
                    }
                }
                order.setOrderPrice(orderPrice);
                order.setPayment(orderPrice);
                order.setPostage(orderBean.getPostage());
                order.setBuyerMessage(orderBean.getBuyerMessage());

                result = orderService.addOrder(order);
                if (result == 0 || result == -1) {
                    break;
                }
            }
        }
        if (result != -1) {
            if (result != 0) {
                return new ResultUtil<Object>().setSuccessMsg("新增订单成功");
            } else {
                return new ResultUtil<Object>().setErrorMsg("订单项新增失败");
            }
        } else {
            return new ResultUtil<Object>().setErrorMsg("新增订单失败");
        }
    }

}