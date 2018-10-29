package com.example.myproject.controller;

import com.example.myproject.pojo.Brand;
import com.example.myproject.service.BrandService;
import com.example.myproject.serviceImpl.GoodsServiceImplTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BrandControllerTest {

    private static final Logger logger = LoggerFactory.getLogger(BrandControllerTest.class);

    @Autowired
    private BrandService brandService;
    private Brand brand;

    @Before
    public void setUp() throws Exception {
        brand = new Brand();
        brand.setBrandName("心相印");
        brand.setBrandDesc("心相印把海水吸干");
        logger.info("加在成功");
    }

    @Test
    public void addBrand() {
        brandService.addBrand(brand);
    }
}