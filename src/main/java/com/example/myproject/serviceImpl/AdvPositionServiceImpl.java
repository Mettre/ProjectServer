package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.AdvPositionMapper;
import com.example.myproject.pojo.AdPosition;
import com.example.myproject.service.AdvPositionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class AdvPositionServiceImpl implements AdvPositionService {

    @Autowired
    private AdvPositionMapper advPositionMapper;


    @Override
    public int updateAdvPosition(AdPosition adPosition) {
        return advPositionMapper.updateAdvPosition(adPosition);
    }

    @Override
    public int addAdvPosition(AdPosition adPosition) {
        return advPositionMapper.addAdvPosition(adPosition);
    }

    @Override
    public int deleteAdvPosition(Long adPositionId) {
        return advPositionMapper.deleteAdvPosition(adPositionId);
    }

    @Override
    public List<AdPosition> findAllAdvPosition() {
        return advPositionMapper.findAllAdvPosition();
    }

    @Override
    public List<AdPosition> findSpecificAdvPosition(Long adPositionId, String adPositionName,String adPositionNo) {
        return advPositionMapper.findSpecificAdvPosition(adPositionId, adPositionName,adPositionNo);
    }
}
