package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 广告位
 */
@Data
@Entity
@Table(name = "t_ad_position")
@TableName("t_ad_position")
public class AdPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "广告位id", hidden = true)
    private Long adPositionId;

    @ApiModelProperty(value = "广告位名称")
    private String adPositionName;

    @ApiModelProperty(value = "广告位描述")
    private String adPositionDescribe;

    @ApiModelProperty(value = "广告位宽度")
    private int adWidth;

    @ApiModelProperty(value = "广告位高度")
    private int adHeight;

    @ApiModelProperty(value = "广告位示例图片")
    private String examplesImage;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "广告位的添加时间", hidden = true)
    private Date createTime = new Date();


    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最近一次更新广告位的时间", hidden = true)
    private Date lastUpdate = new Date();

}