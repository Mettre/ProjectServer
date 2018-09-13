package com.example.myproject.mapper;

import com.example.myproject.pojo.Goods;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface GoodsMapper {

    int addGoods(Goods goods);

    int deleteGoods(int id);

    int modifyGoods(Goods goods);

    List<Goods> findGoods(@Param(value = "goods")Goods goods, @Param(value = "limit") int limit, @Param(value = "offset") int size);

    Goods findGoodDetails(@Param(value="goodsId") long goodsId);

    BigDecimal findPrice(Long goodsId);

    List<Goods> promotionGoods(@Param(value="limit")int limit, @Param(value="offset")int offset);
}
