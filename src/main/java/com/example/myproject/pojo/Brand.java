package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 品牌表
 */
@Data
@Entity
@Table(name = "t_brand")
@TableName("t_brand")
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "品牌id", hidden = true)
    private Long brandId;

    @ApiModelProperty(value = "品牌名称")
    private String brandName;

    @ApiModelProperty(value = "是否显示")
    private Boolean isShow;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "品牌的添加时间", hidden = true)
    private Date createTime = new Date();

    @ApiModelProperty(value = "品牌的详细描述")
    private String brandDesc;

    @ApiModelProperty(value = "是否推荐")
    private Boolean recommend;
}
