package com.example.myproject.pojo;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 商品分类
 */
@Data
@Entity
@Table(name = "t_category")
@TableName("t_category")
public class Category {

    @Id
    @ApiModelProperty(value = "分类id自增", hidden = true)
    private int categoryId;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "分类描述")
    private String categoryDescribe;

    @ApiModelProperty(value = "分类的关键字,可能是为了搜索")
    private String keywords;

    @ApiModelProperty(value = "分类的父类ID，取值于该表的categoryId字段")
    private int parentId;

    @ApiModelProperty(value = "是否推荐")
    private boolean recommend;

    @ApiModelProperty(value = "是否显示")
    private boolean isShow;

    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", hidden = true)
    private Date creationTime = new Date();
}
