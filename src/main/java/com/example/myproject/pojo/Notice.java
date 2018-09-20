package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.example.myproject.utils.SnowFlakeUtil;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 公告
 */
@Data
@Entity
@Table(name = "t_notice")
@TableName("t_notice")
public class Notice {

    @Id
    @ApiModelProperty(value = "公告id", hidden = true)
    private Long noticeId;

    @ApiModelProperty(value = "公告名称")
    private String noticeName;

    @ApiModelProperty(value = "公告描述")
    private String noticeDescribe;

    @ApiModelProperty(value = "公告链接地址")
    private String noticeLink;

    @ApiModelProperty(value = "公告阅读数", hidden = true)
    private Integer readCount = 0;

    @ApiModelProperty(value = "公告推送的用户类型  1：所有人 2：群体 3：单个用户")
    private Integer noticeType;

    @CreatedDate
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date creationTime = new Date();

}
