package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠规则
 */
@Data
@Entity
@Table(name = "t_discount")
@TableName("t_discount")
public class DiscountRule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "优惠规则id", hidden = true)
    private Long discountId;

    @ApiModelProperty(value = "优惠规则名称")
    private String discountName;

    @ApiModelProperty(value = "优惠范围；0，全部商品；1，按分类；2，按品牌；3，按商品")
    private int discountRange;

    @ApiModelProperty(value = "优惠范围；0，全部商品；1，按分类；2，按品牌；3，按商品")
    private String discountRangeText;

    @ApiModelProperty(value = "如果是商品，该处是商品的id，如果是品牌，该处是品牌的id")
    private String discountRangeId;

    @ApiModelProperty(value = "订单达到金额下限，才参加活动")
    private BigDecimal minAmount;

    @ApiModelProperty(value = "订单达到金额上限，才参加活动")
    private BigDecimal maxAmount;

    @ApiModelProperty(value = "减免金额")
    private BigDecimal reduction;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date creationTime = new Date();

}
