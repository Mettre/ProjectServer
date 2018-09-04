package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 分组--用户 / 关联表
 */
@Data
@Entity
@Table(name = "t_group_relation_user")
@TableName("t_group_relation_user")
public class GroupRelationUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "关联id", hidden = true)
    private Long relationId;

    @ApiModelProperty(value = "分组id", hidden = true)
    private Long groupId;

    @ApiModelProperty(value = "用户id", hidden = true)
    private Long userId;
}
