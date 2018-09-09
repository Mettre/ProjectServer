package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.OrderMapper;
import com.example.myproject.pojo.Goods;
import com.example.myproject.pojo.Order;
import com.example.myproject.service.OrderService;
import com.example.myproject.vojo.OrderRequestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

@Slf4j
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;


    @Override
    public int addOrder(Order order) {
        return orderMapper.addOrder(order);
    }

    @Override
    public int addOrderItem(Goods goods, Long orderId, Long cartId, int goodsNumber, BigDecimal totalPrice,Date creationTime) {
        return orderMapper.addOrderItem(goods, orderId, cartId, goodsNumber, totalPrice,creationTime);
    }
}
