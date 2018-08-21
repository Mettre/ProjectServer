package com.example.myproject.controller;

import com.example.myproject.pojo.Category;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品
 */
@Slf4j
@RestController
@RequestMapping("/api/shop")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @RequestMapping(value = "/category/addCategory", method = RequestMethod.GET)
    @ApiOperation(value = "新增商品分类")
    public Result<Object> addCategory(@RequestBody Category Category) {
        int result = categoryService.addCategory(Category);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("添加分类失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("添加分类成功");
    }

    @RequestMapping(value = "/category/deleteCategory", method = RequestMethod.POST)
    @ApiOperation(value = "删除商品分类")
    public Result<Object> deleteDelivery(@RequestParam String categoryIds) {
        String ids[] = categoryIds.split(",");
        int status = 0;
        for (String addressId : ids) {
            status = categoryService.deleteCategory(addressId);
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
    }

}
