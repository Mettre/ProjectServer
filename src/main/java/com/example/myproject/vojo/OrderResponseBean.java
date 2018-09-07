package com.example.myproject.vojo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class OrderResponseBean {

    private Long orderId;//订单id
    private Long orderNo;//订单编号
    private BigDecimal orderPrice;//订单总金额
    private BigDecimal payment;//总支付金额
    private int paymentType;//付款类型 1:在线付款  2：线下付款
    private BigDecimal postage;//邮费
    private String buyerMessage;//买家留言
    private Integer status;//订单状态 0:已取消  10:未付款  20:已支付  30:已发货  40:交易成功  50:交易关闭
    private Date paymentTime;//支付时间
    private Date sendTime;//发货时间
    private Date completeTime;//交易完成时间
    private Date closeTime;//交易关闭时间
    private Date creationTime;//创建时间
    private List<OrderGoodsBean> orderItem;

}
