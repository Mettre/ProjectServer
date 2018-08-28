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
 * 订单
 */
@Data
@Entity
@Table(name = "t_order")
@TableName("t_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "订单id", hidden = true)
    private Long orderId;

    @ApiModelProperty(value = "订单编号", hidden = true)
    private Long orderNo;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "购物车id,订单成功后清除该id下的购物车")
    private Long shopId;

    @ApiModelProperty(value = "实际付款金额")
    private BigDecimal payment;

    @ApiModelProperty(value = "付款类型 1:在线付款  2：线下付款")
    private int paymentType;

    @ApiModelProperty(value = "邮费")
    private int postage;

    @ApiModelProperty(value = "订单状态 0:已取消  10:未付款  20:已支付  30:已发货  40:交易成功  50:交易关闭")
    private int status;

    @ApiModelProperty(value = "支付时间")
    private Date paymentTime;

    @ApiModelProperty(value = "发货时间")
    private Date sendTime;

    @ApiModelProperty(value = "交易完成时间")
    private Date completeTime;

    @ApiModelProperty(value = "交易关闭时间")
    private Date closeTime;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date creationTime = new Date();

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date lastUpdate = new Date();


}
