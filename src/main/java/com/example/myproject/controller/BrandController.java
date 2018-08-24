package com.example.myproject.controller;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.pojo.Brand;
import com.example.myproject.pojo.Result;
import com.example.myproject.pojo.ResultUtil;
import com.example.myproject.service.BrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/shop")
@Api(description = "品牌")
public class BrandController {

    @Autowired
    public BrandService brandService;

    @RequestMapping(value = "/brand/addBrand", method = RequestMethod.POST)
    @ApiOperation(value = "新增品牌分类")
    public Result<Object> addBrand(@ModelAttribute Brand brand) {

        if (StrUtil.isNotBlank(brand.getBrandName())) {
            List<Brand> categoryList3 = brandService.findBrand(brand.getBrandName());
            if (categoryList3 != null && categoryList3.size() > 0) {
                return new ResultUtil<Object>().setErrorMsg("该品牌已存在");
            }
        }else {
            return new ResultUtil<Object>().setErrorMsg("请输入品牌名");
        }

        int result = brandService.addBrand(brand);
        if (result != 1) {
            return new ResultUtil<Object>().setErrorMsg("添加品牌失败");
        }
        return new ResultUtil<Object>().setSuccessMsg("添加品牌成功");
    }

    @RequestMapping(value = "/brand/deleteBrand", method = RequestMethod.POST)
    @ApiOperation(value = "删除品牌")
    public Result<Object> deleteDelivery(@RequestParam String brandIds) {
        String ids[] = brandIds.split(",");
        int status = 0;
        if (ids != null && ids.length > 0) {
            for (String brand_id : ids) {
                status = brandService.deleteBrand(Integer.parseInt(brand_id));
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
                return new ResultUtil<Object>().setErrorMsg("删除品牌失败");
            }
        } else {
            return new ResultUtil<Object>().setErrorMsg("入参不正确");
        }
    }

    @RequestMapping(value = "/brand/findBrand", method = RequestMethod.POST)
    @ApiOperation(value = "查找品牌")
    public Result<Object> findBrand(@RequestParam(value = "brandName", required = false) String brandName) {
        List<Brand> categoryList = brandService.findBrand(brandName);
        return new ResultUtil<Object>().setData(categoryList);
    }

    @RequestMapping(value = "/brand/findAllBrand", method = RequestMethod.POST)
    @ApiOperation(value = "查找全部可取品牌")
    public Result<Object> findAllBrand(@RequestParam(value = "isShow", required = false) Boolean isShow, @RequestParam(value = "recommend", required = false) Boolean recommend) {
        List<Brand> categoryList = brandService.findAllBrand(isShow, recommend);
        return new ResultUtil<Object>().setData(categoryList);
    }
}
