package com.example.myproject.service;

import com.example.myproject.pojo.Category;

import java.util.List;

public interface CategoryService {

    int addCategory(Category category);

    int deleteCategory(int id);

    List<Category> findCategory(Integer categoryId, String categoryName);
}
