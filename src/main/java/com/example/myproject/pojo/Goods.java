package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 */
@Data
@Entity
@Table(name = "t_goods")
@TableName("t_goods")
public class Goods {

    @Id
    @ApiModelProperty(value = "商品id自增", hidden = true)
    private Long goodId;

    @ApiModelProperty(value = "商品分类id，取值category的categoryId")
    private int categoryId;

    @ApiModelProperty(value = "商品的唯一货号")
    private Long goodsSn;

    @ApiModelProperty(value = "品牌id，取值于brand 的brandId")
    private int brandId;

    @ApiModelProperty(value = "商品库存数量")
    private int goodsNumber;

    @ApiModelProperty(value = "市场价")
    private BigDecimal marketPrice;

    @ApiModelProperty(value = "本店售价")
    private BigDecimal shopPrice;

    @ApiModelProperty(value = "促销价")
    private BigDecimal promotePrice;

    @ApiModelProperty(value = "促销价格开始日期")
    private Date promoteStartDate;

    @ApiModelProperty(value = "促销价格结束日期")
    private Date promoteEndDate;

    @ApiModelProperty(value = "商品关键字，放在商品页的关键字中，为搜索引擎收录用")
    private String keywords;

    @ApiModelProperty(value = "商品的简短描述")
    private String goodsBrief;

    @ApiModelProperty(value = "商品的详细描述")
    private String goodsDesc;

    @ApiModelProperty(value = "该商品是否开放销售，1，是；0，否")
    private Boolean isSale;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "商品的添加时间", hidden = true)
    private Date createTime;

    @ApiModelProperty(value = "商品是否已经删除，0，否；1，已删除")
    private Boolean isDelete;

    @ApiModelProperty(value = "是否是新品")
    private Boolean isNew;

    @ApiModelProperty(value = "是否热销，0，否；1，是")
    private Boolean isHot;

    @ApiModelProperty(value = "是否特价促销；0，否；1，是")
    private Boolean isPromote;

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最近一次更新商品配置的时间", hidden = true)
    private Date lastUpdate = new Date();

    @ApiModelProperty(value = "商品的商家备注，仅商家可见")
    private String sellerNote;

}
