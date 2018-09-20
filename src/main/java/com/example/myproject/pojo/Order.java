package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.myproject.utils.SnowFlakeUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

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
    private Long orderId = SnowFlakeUtil.getFlowIdInstance().nextId();

    @ApiModelProperty(value = "订单编号", hidden = true)
    private Long orderNo = SnowFlakeUtil.getFlowIdInstance().nextId();

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;

    @ApiModelProperty(value = "订单总金额", hidden = true)
    private BigDecimal orderPrice;

    @ApiModelProperty(value = "总支付金额", hidden = true)
    private BigDecimal payment;

    @ApiModelProperty(value = "付款类型 1:在线付款  2：线下付款")
    private Integer paymentType;

    @ApiModelProperty(value = "邮费", hidden = true)
    private BigDecimal postage;

    @ApiModelProperty(value = "买家留言")
    private String buyerMessage;

    @ApiModelProperty(value = "品牌id")
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "订单状态 0:已取消  10:未付款  20:已支付  30:已发货  40:交易成功  50:交易关闭", hidden = true)
    private Integer status = 10;

    @ApiModelProperty(value = "支付时间", hidden = true)
    private Date paymentTime;

    @ApiModelProperty(value = "发货时间", hidden = true)
    private Date sendTime;

    @ApiModelProperty(value = "交易完成时间", hidden = true)
    private Date completeTime;

    @ApiModelProperty(value = "交易关闭时间", hidden = true)
    private Date closeTime;

    @CreatedDate
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date creationTime = new Date();

    @LastModifiedDate
    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date lastUpdate = new Date();

    @ApiModelProperty(value = "收货地址", hidden = true)
    private String recipientAddress;

    @ApiModelProperty(value = "收货姓名", hidden = true)
    private String recipientName;

    @ApiModelProperty(value = "收货电话号", hidden = true)
    private String recipientPhoneNumber;


}
