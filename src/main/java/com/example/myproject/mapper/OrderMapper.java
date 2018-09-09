package com.example.myproject.mapper;

import com.example.myproject.pojo.Goods;
import com.example.myproject.pojo.Order;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;

public interface OrderMapper {

    int addOrder(Order order);

    int addOrderItem(@Param(value = "goods") Goods goods, @Param(value = "orderId") Long orderId, @Param(value = "cartId") Long cartId, @Param(value = "goodsNumber") int goodsNumber, @Param(value = "totalPrice") BigDecimal totalPrice, @Param(value = "creationTime") Date creationTime);

}
