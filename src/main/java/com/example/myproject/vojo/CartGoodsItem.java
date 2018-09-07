package com.example.myproject.vojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartGoodsItem {

    private Long cartId;//购物车id
    private Long goodsId;//商品id
    private BigDecimal cartPrice;//加入购物车时的价格
    private int cartNumber;//购物车数量
    private String goodsName;//商品名称
    private String goodsBrief;//商品描述
    private BigDecimal goodsPrice;//商品价格
    private int stock;//商品库存
}
