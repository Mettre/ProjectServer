package com.example.myproject.serviceImpl;

import com.example.myproject.exception.CustomerException;
import com.example.myproject.exception.ErrorCode;
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

    public String getSay(){
        return "你好";
    }

    @Override
    public Goods findGoodDetails(Long goodsId) {
        if (goodsId <= 0) {
            throw new CustomerException("商品id不能为空", ErrorCode.PARAMETEREMPTY);
        }
        Goods goodDetails = brandMapper.findGoodDetails(goodsId);
        if (goodDetails == null) {
            throw new CustomerException("商品不存在", "400");
        }
        goodDetails.setCreateTime(null);
        goodDetails.setLastUpdate(null);
        return goodDetails;
    }

    @Override
    public BigDecimal findPrice(Long goodsId) {
        return brandMapper.findPrice(goodsId);
    }

    @Override
    public List<Goods> promotionGoods(int limit, int offset) {
        return brandMapper.promotionGoods(limit, offset);
    }
}
