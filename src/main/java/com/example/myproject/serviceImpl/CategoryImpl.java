package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.CategoryMapper;
import com.example.myproject.pojo.Category;
import com.example.myproject.service.CategoryService;
import com.example.myproject.vojo.CategoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class CategoryImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public int addCategory(Category category) {
        return categoryMapper.addCategory(category);
    }

    @Override
    public int modifyCategory(Category category) {
        return categoryMapper.modifyCategory(category);
    }

    @Override
    public int deleteCategory(int category_id) {
        return categoryMapper.deleteCategory(category_id);
    }

    @Override
    public List<Category> findCategory(Integer categoryId, String categoryName,Integer parentId) {
        return categoryMapper.findCategory(categoryId,categoryName,parentId);
    }

    @Override
    public List<CategoryBean> findAllCategory(Boolean isShow) {
        return categoryMapper.findAllCategory(isShow);
    }
}
