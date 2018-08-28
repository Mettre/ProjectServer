package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 广告
 */
@Data
@Entity
@Table(name = "t_ad")
@TableName("t_ad")
public class Ad {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "广告id", hidden = true)
    private Long adId;

    @ApiModelProperty(value = "广告位id")
    private Long adPositionId;

    @ApiModelProperty(value = "广告位名称")
    private String adName;

    @ApiModelProperty(value = "广告位描述")
    private String adDescribe;

    @ApiModelProperty(value = "广告属性---1：跳转H5  2：跳转商品  3：公告")
    private int adType;

    @ApiModelProperty(value = "广告链接地址")
    private String adLink;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "广告位的开始时间")
    private Date startTime = new Date();

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "广告位的结束时间")
    private Date endTime = new Date();

    @ApiModelProperty(value = "广告图片")
    private String adImage;

    @ApiModelProperty(value = "该广告是否关闭;1开启; 0关闭; 关闭后广告将不再有效")
    private boolean enabled;

    @ApiModelProperty(value = "该广告点击数")
    private int clickCount;

}
