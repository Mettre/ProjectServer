package com.example.myproject.vojo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderGoodsBean {

    private Long goodsId;//商品id
    private BigDecimal orderGoodsPrice;//提交订单时的价格
    private int quantity;//提交订单的商品数量
    private String goodsName;//商品名称
    private String goodsBrief;//商品描述
    private Long orderItemId;
}
