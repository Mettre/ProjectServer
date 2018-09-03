package com.example.myproject.mapper;

import com.example.myproject.pojo.Adv;
import com.example.myproject.pojo.AdPosition;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdvPositionMapper {

    int updateAdvPosition(AdPosition adPosition);

    int addAdvPosition(AdPosition adPosition);

    int deleteAdvPosition(Long adPositionId);

    List<AdPosition> findAllAdvPosition();

    List<AdPosition> findSpecificAdvPosition(@Param(value = "adPositionId") Long adPositionId, @Param(value = "adPositionName") String adPositionName,@Param(value = "adPositionNo") String adPositionNo);

    int addAd(Adv adv);
}
