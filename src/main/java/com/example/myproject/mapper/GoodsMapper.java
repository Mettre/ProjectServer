package com.example.myproject.mapper;

import com.example.myproject.pojo.Goods;

import java.util.List;

public interface GoodsMapper {

    int addGoods(Goods goods);

    int deleteGoods(int id);

    List<Goods> findGoods(Goods goods);
}
