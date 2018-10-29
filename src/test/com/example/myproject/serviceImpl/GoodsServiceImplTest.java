package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.GoodsMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GoodsServiceImplTest {

    @Autowired
    private GoodsServiceImpl goodsService;

    @Autowired
    private GoodsMapper brandMapper;

    private static final Logger logger = LoggerFactory.getLogger(GoodsServiceImplTest.class);

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void findGoodDetails() {
        logger.info(goodsService.getSay());
        brandMapper.findGoodDetails(2L);
    }
}