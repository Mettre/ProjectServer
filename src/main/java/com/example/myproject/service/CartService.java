package com.example.myproject.service;

import com.example.myproject.pojo.Cart;
import com.example.myproject.vojo.CartBean;

import java.util.List;

public interface CartService {

    int addCart(Cart cart);

    Cart finCartByGoodsId(Long goodsId);

    int editCartNum(Long userId, Long sessionId, Long goodsId, int cartNumber);

    int deleteCart(Long cartId, Long userId, Long sessionId);

    int deleteAllCart(Long userId, Long sessionId);

    List<CartBean> findAllCart(Long userId, Long sessionId);

    int cartMerge(Long sessionId, Long userId);
}
