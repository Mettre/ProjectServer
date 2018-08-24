package com.example.myproject.service;

import com.example.myproject.pojo.Goods;

import java.util.List;

public interface GoodsService {

    int addGoods(Goods goods);

    int deleteGoods(int id);

    List<Goods> findGoods(Goods goods);
}
