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
import java.util.List;

/**
 * 用户分组
 */
@Data
@Entity
@Table(name = "t_group")
@TableName("t_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "分组id", hidden = true)
    private Long groupId;

    @ApiModelProperty(value = "分组名称")
    private String groupName;

    @ApiModelProperty(value = "分组描述")
    private String groupDescribe;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "商品的添加时间", hidden = true)
    private Date createTime = new Date();

    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "最近一次更新商品配置的时间", hidden = true)
    private Date lastUpdate = new Date();

    @Transient
    private List<Users> users;
}
