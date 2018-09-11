package com.example.myproject.service;

import com.example.myproject.pojo.Brand;

import java.util.List;

public interface BrandService {

    int addBrand(Brand brand);

    int deleteBrand(int id);

    List<Brand> findBrand(String BrandName);

    List<Brand> findAllBrand(Boolean isShow, Boolean recommend);

    Brand findBrandById(Long brandId);
}
