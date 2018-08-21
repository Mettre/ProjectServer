package com.example.myproject.service;

import com.example.myproject.pojo.Category;

public interface CategoryService {

    int addCategory(Category category);

    int deleteCategory(String id);
}
