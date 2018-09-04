package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 公告对象
 */
@Data
@Entity
@Table(name = "t_notice_user")
@TableName("t_notice_user")
public class NoticeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @ApiModelProperty(value = "公告对象id", hidden = true)
    private Long noticeUserId;

    @ApiModelProperty(value = "公告id")
    private Long noticeId;

    @ApiModelProperty(value = "个人编号")
    private Long userId;

    @ApiModelProperty(value = "用户群体")
    private Integer noticeGroup;

    @ApiModelProperty(value = "所有用户群体")
    private Boolean allUser;

}
