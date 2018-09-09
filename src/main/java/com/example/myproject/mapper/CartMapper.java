package com.example.myproject.mapper;

import com.example.myproject.pojo.Cart;
import com.example.myproject.vojo.CartBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {

    int addCart(Cart cart);

    int editCartNum(@Param(value = "userId") Long userId, @Param(value = "sessionId") Long sessionId, @Param(value = "goodsId") Long goodsId, @Param(value = "cartNumber") int cartNumber);

    Cart finCartByGoodsId(Long goodsId);

    int deleteCart(Long cartId);

    int deleteAllCart(Long userId, Long sessionId);

    List<CartBean> findAllCart(@Param(value = "userId") Long userId, @Param(value = "sessionId") Long sessionId);

}
