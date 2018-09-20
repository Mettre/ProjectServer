package com.example.myproject.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 支付信息
 */
@Data
@Entity
@Table(name = "t_pay")
@TableName("t_pay")
public class PayInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "支付id", hidden = true)
    private Long payId;

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "订单编号")
    private Long orderNo;

    @ApiModelProperty(value = "支付平台")
    private int payType;

    @ApiModelProperty(value = "支付平台流水号")
    private Long payNumber;

    @ApiModelProperty(value = "支付宝支付状态")
    private String payStatus;

    @CreatedDate
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date creationTime = new Date();

    @LastModifiedDate


    @ApiModelProperty(value = "更新时间", hidden = true)
    private Date lastUpdate = new Date();

}
