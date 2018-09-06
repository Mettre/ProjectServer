package com.example.myproject.mapper;

import com.example.myproject.pojo.Cart;
import com.example.myproject.vojo.CartBean;
import org.apache.ibatis.annotations.Param;

public interface CartMapper {

    int addCart(Cart cart);

    int addCartNum(Long goodsId, int cartNumber);

    Cart finCartByGoodsId(Long goodsId);

    int deleteCart(Long cartId);

    int deleteAllCart(Long userId, Long sessionId);

    CartBean findAllCart(@Param(value = "userId") Long userId,@Param(value = "sessionId") Long sessionId);

}
