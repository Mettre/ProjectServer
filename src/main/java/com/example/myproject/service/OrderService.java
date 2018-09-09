package com.example.myproject.service;

import com.example.myproject.pojo.Goods;
import com.example.myproject.pojo.Order;

import java.math.BigDecimal;
import java.util.Date;

public interface OrderService {

    int addOrder(Order order);

    int addOrderItem(Goods goods, Long orderId, Long cartId, int goodsNumber, BigDecimal totalPrice,Date creationTime);

}
