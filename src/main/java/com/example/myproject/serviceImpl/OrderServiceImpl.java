package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.OrderMapper;
import com.example.myproject.pojo.Order;
import com.example.myproject.service.OrderService;
import com.example.myproject.vojo.OrderRequestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
