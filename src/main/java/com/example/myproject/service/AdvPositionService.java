package com.example.myproject.service;

import com.example.myproject.pojo.AdPosition;

import java.util.List;

public interface AdvPositionService {

    int updateAdvPosition(AdPosition adPosition);

    int addAdvPosition(AdPosition adPosition);

    int deleteAdvPosition(Long adPositionId);

    List<AdPosition> findAllAdvPosition();

    List<AdPosition> findSpecificAdvPosition(Long adPositionId, String adPositionName,String adPositionNo);
}
