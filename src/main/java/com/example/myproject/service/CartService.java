package com.example.myproject.service;

import com.example.myproject.pojo.Cart;

public interface CartService {

    int addCart(Cart cart);

    Cart finCartByGoodsId(Long goodsId);

    int addCartNum(Long goodsId,int cartNumber);

    int deleteCart(Long cartId);

    int deleteAllCart(Long userId);
}
