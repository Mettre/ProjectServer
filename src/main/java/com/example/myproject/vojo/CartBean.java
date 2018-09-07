package com.example.myproject.vojo;

import lombok.Data;

import java.util.List;

@Data
public class CartBean {

    private int brandId;//品牌id
    private String brandName;//品牌名
    private List<CartGoodsItem> goodsItem;

}
