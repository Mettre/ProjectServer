package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.pojo.Category;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.service.CategoryService;
import com.example.myproject.vojo.CategoryBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
@Api(description = "商城相关")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @RequestMapping(value = "/category/addCategory", method = RequestMethod.POST)
    @ApiOperation(value = "新增商品分类")
    public Result<Object> addCategory(@ModelAttribute Category category) {

        if (category.getCategoryId() > 0) {
            List<Category> categoryList3 = categoryService.findCategory(category.getCategoryId(), null);
            if (categoryList3 != null && categoryList3.size() > 0) {
                return new ResultUtil<Object>().setErrorMsg("该分类已存在");
            }
        }

        if (StrUtil.isNotBlank(category.getCategoryName())) {
            List<Category> categoryList3 = categoryService.findCategory(null, category.getCategoryName());
            if (categoryList3 != null && categoryList3.size() > 0) {
                return new ResultUtil<Object>().setErrorMsg("该分类已存在");
            }
        }
        if (category.getParentId() > 0) {
            List<Category> categoryList2 = categoryService.findCategory(category.getParentId(), null);
            if (categoryList2 == null || categoryList2.size() == 0) {
                return new ResultUtil<Object>().setErrorMsg("父分类不存在");
            }
        }

        int result = categoryService.addCategory(category);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("添加分类失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("添加分类成功");
    }

    @RequestMapping(value = "/category/findCategory", method = RequestMethod.POST)
    @ApiOperation(value = "查找具体分类")
    public Result<Object> findCategory(@RequestParam(value = "categoryId", required = false) Integer categoryId, @RequestParam(value = "categoryName", required = false) String categoryName) {
        List<Category> categoryList = categoryService.findCategory(categoryId, categoryName);
        return new ResultUtil<Object>().setData(categoryList);
    }


    @RequestMapping(value = "/category/findAllCategory", method = RequestMethod.POST)
    @ApiOperation(value = "多级分类列表")
    public Result<Object> findAllCategory(@RequestParam(value = "isShow", required = false) Boolean isShow, @RequestParam(value = "recommend", required = false) Boolean recommend) {
        List<CategoryBean> categoryList = categoryService.findAllCategory(isShow, recommend);
        return new ResultUtil<Object>().setData(getCategoryList(categoryList, 0));
    }

    /**
     * 递归分类
     *
     * @param categoryAllList
     * @param parentId
     * @return
     */
    private List<CategoryBean> getCategoryList(List<CategoryBean> categoryAllList, int parentId) {
        List<CategoryBean> categoryParentList = new ArrayList<>();
        List<CategoryBean> categoryChildList = new ArrayList<>();

        if (categoryAllList != null && categoryAllList.size() > 0) {
            for (CategoryBean categoryList : categoryAllList) {
                if (categoryList.getParentId() == parentId) {
                    categoryParentList.add(categoryList);
                } else {
                    categoryChildList.add(categoryList);
                }
            }
        }

        if (categoryParentList != null && categoryParentList.size() > 0) {
            for (CategoryBean categoryList : categoryParentList) {
                categoryList.setChildCategory(getCategoryList(categoryChildList, categoryList.getCategoryId()));
            }
        }
        return categoryParentList;
    }


    @RequestMapping(value = "/category/deleteCategory", method = RequestMethod.POST)
    @ApiOperation(value = "删除商品分类")
    public Result<Object> deleteDelivery(@RequestParam String categoryIds) {
        String ids[] = categoryIds.split(",");
        int status = 0;
        if (ids != null && ids.length > 0) {
            for (String category_id : ids) {
                status = categoryService.deleteCategory(Integer.parseInt(category_id));
                if (status == 0 || status == -1) {
                    break;
                }
            }
            if (status != -1) {
                if (status != 0) {
                    return new ResultUtil<Object>().setSuccessMsg("删除成功");
                } else {
                    return new ResultUtil<Object>().setErrorMsg("有不存在的id,删除失败");
                }
            } else {
                return new ResultUtil<Object>().setErrorMsg("删除商品分类失败");
            }
        } else {
            return new ResultUtil<Object>().setErrorMsg("入参不正确");
        }

    }

}
