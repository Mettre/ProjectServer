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
    @ApiModelProperty(value = "商品的添加时间", hidden = true)
    private Date createTime = new Date();

    @LastModifiedDate
    @ApiModelProperty(value = "最近一次更新商品配置的时间", hidden = true)
    private Date lastUpdate = new Date();

    @Transient
    @ApiModelProperty(value = "用户信息", hidden = true)
    private List<Users> users;
}
