package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
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

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

    @ApiModelProperty(value = "设备id 如果该用户退出,该Session_id对应的购物车中所有记录都将被删除")
    private Long sessionId;

    @ApiModelProperty(value = "商品id")
    private Long goodsId;

    @ApiModelProperty(value = "加入购物车时的价格",hidden = true)
    private BigDecimal cartPrice;

    @ApiModelProperty(value = "购物车数量", hidden = true)
    private int cartNumber;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date createTime = new Date();

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最近一次更新购物车的时间", hidden = true)
    private Date lastUpdate = new Date();
}
