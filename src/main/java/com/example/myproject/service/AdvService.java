package com.example.myproject.service;

import com.example.myproject.pojo.Adv;

import java.util.List;

public interface AdvService {

    int addAdv(Adv adv);

    int addRead(Long adId,Integer clickCount);

    List<Adv> findSpecificAdv(Long adPositionId, Long adId, String adName);
}
