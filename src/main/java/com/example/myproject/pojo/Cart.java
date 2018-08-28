package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 购物车
 */
@Data
@Entity
@Table(name = "t_cart")
@TableName("t_cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "购物车id", hidden = true)
    private Long cartId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "设备id 如果该用户退出,该Session_id对应的购物车中所有记录都将被删除")
    private Long sessionId;

    @ApiModelProperty(value = "品牌id")
    private Long brandId;

    @ApiModelProperty(value = "品牌名称", hidden = true)
    private String brandName;

    @ApiModelProperty(value = "商品id")
    private Long goodsId;

    @ApiModelProperty(value = "商品名称", hidden = true)
    private String goodsName;

    @ApiModelProperty(value = "市场价")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "本店售价")
    private BigDecimal shopPrice;

    @ApiModelProperty(value = "商品的唯一货号", hidden = true)
    private Long goodsSn;

    @ApiModelProperty(value = "购物车数量")
    private int cartNumber;

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最近一次更新购物车的时间", hidden = true)
    private Date lastUpdate = new Date();
}
