package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
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
public class Adv {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "广告id", hidden = true)
    private Long adId;

    @Version
    private Integer version;

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

    @ApiModelProperty(value = "广告位的开始时间")
    private Date startTime = new Date();

    @ApiModelProperty(value = "广告位的结束时间")
    private Date endTime;

    @ApiModelProperty(value = "广告图片")
    private String adImage;

    @ApiModelProperty(value = "该广告是否关闭;1开启; 0关闭; 关闭后广告将不再有效")
    private boolean enabled = true;

    @ApiModelProperty(value = "该广告点击数", hidden = true)
    private int clickCount = 0;

}
