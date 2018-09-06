package com.example.myproject.mapper;

import com.example.myproject.pojo.Cart;

public interface CartMapper {

    int addCart(Cart cart);

    int addCartNum(Long goodsId, int cartNumber);

    Cart finCartByGoodsId(Long goodsId);

    int deleteCart(Long cartId);

    int deleteAllCart(Long userId);

}
