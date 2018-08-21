package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.CategoryMapper;
import com.example.myproject.pojo.Category;
import com.example.myproject.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CategoryService")
public class CategoryImpl implements CategoryService {

    private CategoryMapper userMapper;

    @Autowired
    public void setUserMapper(CategoryMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public int addCategory(Category category) {
        return userMapper.addCategory(category);
    }
}
