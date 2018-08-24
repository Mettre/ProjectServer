package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.BrandMapper;
import com.example.myproject.pojo.Brand;
import com.example.myproject.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public int addBrand(Brand brand) {
        return brandMapper.addBrand(brand);
    }

    @Override
    public int deleteBrand(int id) {
        return brandMapper.deleteBrand(id);
    }

    @Override
    public List<Brand> findBrand(String brandName) {
        return brandMapper.findBrand(brandName);
    }

    @Override
    public List<Brand> findAllBrand(Boolean isShow, Boolean recommend) {
        return brandMapper.findAllBrand(isShow, recommend);
    }
}
