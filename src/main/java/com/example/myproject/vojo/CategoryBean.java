package com.example.myproject.vojo;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.util.List;

/**
 * 分类列表 大小分类集合
 */
@Data
public class CategoryBean {

    /**
     * 分类id
     */
    private int categoryId;

    /**
     * 分类名称
     */
    private String categoryName;

    /**
     * 父分类Id
     */
    private int parentId;

    /**
     * 分类列表
     */
    private List<CategoryBean> childCategory;
}
