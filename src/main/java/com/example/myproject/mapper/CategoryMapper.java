package com.example.myproject.mapper;

import com.example.myproject.pojo.Category;
import com.example.myproject.vojo.CategoryBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {

    int addCategory(Category category);

    int deleteCategory(int categoryId);

    int modifyCategory(Category category);

    List<Category> findCategory(@Param(value = "categoryId") Integer categoryId, @Param(value = "categoryName") String categoryName,@Param(value = "parentId") Integer parentId);

    List<CategoryBean> findAllCategory(@Param(value = "isShow") Boolean isShow);
}
