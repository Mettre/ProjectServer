package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.example.myproject.base.MettreBaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 收货地址
 */
@Data
@Entity
@Table(name = "t_address")
@TableName("t_address")
public class Address extends MettreBaseEntity {

    @ApiModelProperty(value = "邮寄姓名")
    private String name;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "市")
    private String city;

    @ApiModelProperty(value = "区县")
    private String county;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "详细地址")
    private String address;

    @ApiModelProperty(value = "是否默认")
    private boolean defaults;

    @ApiModelProperty(value = "用户id")
    private String userId;
}
