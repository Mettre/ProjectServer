package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

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
    private String noticeDescribe;
}
