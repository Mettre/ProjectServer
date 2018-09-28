package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.BrandMapper;
import com.example.myproject.pojo.Brand;
import com.example.myproject.service.BrandService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Slf4j
@Service
@Transactional
@CacheConfig(cacheNames="brand")
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    @CacheEvict(allEntries=true)
    public int addBrand(Brand brand) {
        return brandMapper.addBrand(brand);
    }

    @Override
    @CacheEvict(allEntries=true)
    public int deleteBrand(int id) {
        return brandMapper.deleteBrand(id);
    }

    @Override
    @Cacheable(key="'brand '+ #p0")
    public List<Brand> findBrand(String brandName) {
        return brandMapper.findBrand(brandName);
    }

    @Override
    @Cacheable(key="'brand '+#p0 + '-' + #p1")
    public List<Brand> findAllBrand(Boolean isShow, Boolean recommend) {
        return brandMapper.findAllBrand(isShow, recommend);
    }

    @Override
    @Cacheable(key="'brand '+ #p0")
    public Brand findBrandById(Long brandId) {
        return brandMapper.findBrandById(brandId);
    }
}
