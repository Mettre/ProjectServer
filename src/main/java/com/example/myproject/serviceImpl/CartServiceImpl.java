package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.CartMapper;
import com.example.myproject.pojo.Cart;
import com.example.myproject.service.CartService;
import com.example.myproject.vojo.CartBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper categoryMapper;

    @Override
    public int addCart(Cart cart) {
        return categoryMapper.addCart(cart);
    }

    @Override
    public Cart finCartByGoodsId(Long goodsId) {
        return categoryMapper.finCartByGoodsId(goodsId);
    }

    @Override
    public int editCartNum(Long userId, Long sessionId, Long goodsId, int cartNumber) {
        return categoryMapper.editCartNum(userId, sessionId, goodsId, cartNumber);
    }

    @Override
    public int deleteCart(Long cartId, Long userId, Long sessionId) {
        return categoryMapper.deleteCart(cartId, userId, sessionId);
    }

    @Override
    public int deleteAllCart(Long userId, Long sessionId) {
        return categoryMapper.deleteAllCart(userId, sessionId);
    }

    @Override
    public List<CartBean> findAllCart(Long userId, Long sessionId) {
        return categoryMapper.findAllCart(userId, sessionId);
    }

    @Override
    public int cartMerge(Long sessionId, Long userId) {
        return categoryMapper.cartMerge(sessionId, userId);
    }
}
