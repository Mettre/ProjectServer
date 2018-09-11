package com.example.myproject.service;

import com.example.myproject.pojo.Goods;
import com.example.myproject.pojo.Order;
import com.example.myproject.vojo.OrderListBean;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderService {

    int addOrder(Order order);

    int addOrderItem(Goods goods, Long orderId, Long cartId, int goodsNumber, BigDecimal totalPrice, Date creationTime);

    List<OrderListBean> findOrderList(Long userId, Integer status);

}
