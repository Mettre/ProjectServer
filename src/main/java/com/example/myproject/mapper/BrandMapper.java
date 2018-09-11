package com.example.myproject.mapper;

import com.example.myproject.pojo.Brand;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BrandMapper {

    int addBrand(Brand brand);

    int deleteBrand(int id);

    List<Brand> findBrand(@Param(value = "brandName") String brandName);

    List<Brand> findAllBrand(@Param(value = "isShow")Boolean isShow, @Param(value = "recommend") Boolean recommend);

    Brand findBrandById(@Param(value = "brandId") Long brandId);
}
