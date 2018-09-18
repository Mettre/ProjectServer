package com.example.myproject.mapper;

import com.example.myproject.pojo.Adv;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvMapper {

    int addAdv(Adv adv);

    int addAdvRead(@Param(value = "adId") Long adId, @Param(value = "clickCount") Integer clickCount);

    List<Adv> findSpecificAdv(@Param(value = "adPositionId") Long adPositionId, @Param(value = "adId") Long adId, @Param(value = "adName") String adName, @Param(value = "adPositionNo") String adPositionNo);
}
