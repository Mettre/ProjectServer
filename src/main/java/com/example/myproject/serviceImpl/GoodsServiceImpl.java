package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.GoodsMapper;
import com.example.myproject.pojo.Goods;
import com.example.myproject.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper brandMapper;

    @Override
    public int addGoods(Goods goods) {
        return brandMapper.addGoods(goods);
    }

    @Override
    public int modifyGoods(Goods goods) {
        return brandMapper.modifyGoods(goods);
    }

    @Override
    public int deleteGoods(int id) {
        return brandMapper.deleteGoods(id);
    }

    @Override
    public List<Goods> findGoods(Goods goods, int limit, int offset) {
        return brandMapper.findGoods(goods, limit, offset);
    }

    @Override
    public Goods findGoodDetails(Long goodsId) {
        return brandMapper.findGoodDetails(goodsId);
    }


    @Override
    public BigDecimal findPrice(Long goodsId) {
        return brandMapper.findPrice(goodsId);
    }
}
