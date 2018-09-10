package com.example.myproject.vojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class OrderRequestBean {

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

    @ApiModelProperty(value = "邮费", hidden = true)
    private BigDecimal postage;//邮费

    @ApiModelProperty(value = "买家留言")
    private String buyerMessage;//买家留言

    @ApiModelProperty(value = "商品订单")
    private List<GoodsItem> goodsItems;

    @ApiModelProperty(value = "收货地址")
    private String recipientAddress;

    @ApiModelProperty(value = "收货姓名")
    private String recipientName;

    @ApiModelProperty(value = "收货电话")
    private String recipientPhoneNumber;

    @ApiModelProperty(value = "商品总价")
    private BigDecimal goodsTotal;

    @ApiModelProperty(value = "订单总价（商品总价+邮费）")
    private BigDecimal userAllPrice;

    @Data
    public static class GoodsItem {
        @ApiModelProperty(value = "商品id")
        private Long goodsId;

        @ApiModelProperty(value = "购物车id,订单成功后清除该id下的购物车")
        private Long cartId;

        @ApiModelProperty(value = "单个订单商品数量")
        private int goodsNumber;
    }

}
