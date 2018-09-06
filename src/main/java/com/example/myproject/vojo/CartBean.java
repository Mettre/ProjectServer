package com.example.myproject.vojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartBean {

    private Long cartId;
    private int brandId;//品牌id
    private String brandName;//品牌名
    private List<GoodsItem> goodsItem;

    @Data
    public static class GoodsItem {
        private Long goodsId;//商品id
        private BigDecimal cartPrice;//加入购物车时的价格
        private int cartNumber;//购物车数量
        private String goodsName;//商品名称
        private String goodsBrief;//商品描述
        private BigDecimal goodsPrice;//商品价格
    }

}
