package com.example.myproject.service;

import com.example.myproject.pojo.Category;
import com.example.myproject.vojo.CategoryBean;

import java.util.List;

public interface CategoryService {

    int addCategory(Category category);

    int deleteCategory(int id);

    List<Category> findCategory(Integer categoryId, String categoryName);

    List<CategoryBean> findAllCategory(Boolean isShow);
}
