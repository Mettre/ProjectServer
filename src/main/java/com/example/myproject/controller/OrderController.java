package com.example.myproject.controller;

import com.example.myproject.pojo.*;
import com.example.myproject.service.BrandService;
import com.example.myproject.service.CartService;
import com.example.myproject.service.GoodsService;
import com.example.myproject.service.OrderService;
import com.example.myproject.vojo.OrderListBean;
import com.example.myproject.vojo.OrderRequestBean;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/shop")
@Api(description = "订单")
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public GoodsService goodsService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "/loginEd/order/addOrder", method = RequestMethod.POST)
    @ApiOperation(value = "新增订单")
    public Result<Object> addOrder(HttpServletRequest request, @RequestBody List<OrderRequestBean> orderBeanList) {

        List<Long> cartIds = new ArrayList<>();
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
                int result2 = 0;
                for (OrderRequestBean.GoodsItem goodsItem : orderBean.getGoodsItems()) {
                    Goods goods = goodsService.findGoodDetails(goodsItem.getGoodsId());
                    if (orderPrice == null) {
                        orderPrice = goods.getShopPrice().multiply(new BigDecimal(goodsItem.getGoodsNumber()));
                    } else {
                        orderPrice = orderPrice.add(goods.getShopPrice().multiply(new BigDecimal(goodsItem.getGoodsNumber())));
                    }
                    logger.info(order.getOrderId() + "------------------");
                    result2 = orderService.addOrderItem(goods, order.getOrderId(), goodsItem.getCartId(), goodsItem.getGoodsNumber(), goods.getShopPrice().multiply(new BigDecimal(goodsItem.getGoodsNumber())), order.getCreationTime());
                    if (goodsItem.getCartId() != null && goodsItem.getCartId() > 0) {
                        cartIds.add(goodsItem.getCartId());
                    }
                    if (result2 == 0 || result2 == -1) {
                        break;
                    }
                }

                if (result2 > 0 && orderPrice.compareTo(orderBean.getGoodsTotal()) == 0) {

                    Brand brand = brandService.findBrandById(orderBean.getBrandId());
                    if (brand == null) {
                        break;
                    }
                    order.setOrderPrice(orderPrice);
                    order.setPayment(orderPrice);
                    order.setPostage(orderBean.getPostage());
                    order.setBuyerMessage(orderBean.getBuyerMessage());
                    order.setBrandId(orderBean.getBrandId());
                    order.setBrandName(brand.getBrandName());
                    result = orderService.addOrder(order);
                    if (result == 0 || result == -1) {
                        break;
                    }
                } else {
                    break;
                }
            }
        }

        if (result != -1) {
            if (result != 0) {
                int status3 = 1;
                if (cartIds != null && cartIds.size() > 0) {
                    for (Long cartId : cartIds) {
                        status3 = cartService.deleteCart(cartId, Long.parseLong(userId), null);
                        if (status3 == 0 || status3 == -1) {
                            break;
                        }
                    }
                }
                if (status3 >= 1) {
                    return new ResultUtil<Object>().setSuccessMsg("新增订单成功");
                } else {
                    return new ResultUtil<Object>().setErrorMsg("删除购物车失败");
                }
            } else {
                return new ResultUtil<Object>().setErrorMsg("订单项新增失败");
            }
        } else {
            return new ResultUtil<Object>().setErrorMsg("新增订单失败");
        }
    }

    @RequestMapping(value = "/loginEd/order/findOrderList", method = RequestMethod.POST)
    @ApiOperation(value = "订单列表")
    public Result<Object> findOrderList(HttpServletRequest request, Integer status) {
        final Claims claims = (Claims) request.getAttribute("claims");
        String userId = claims.getSubject();
        List<OrderListBean> orderListBeanList = orderService.findOrderList(Long.parseLong(userId), status);
        return new ResultUtil<Object>().setData(orderListBeanList);
    }
}
