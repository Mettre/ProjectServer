package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.AdvMapper;
import com.example.myproject.pojo.Adv;
import com.example.myproject.service.AdvService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class AdServiceImpl implements AdvService {

    @Autowired
    private AdvMapper addAdv;


    @Override
    public int addAdv(Adv adv) {
        return addAdv.addAdv(adv);
    }

    @Override
    public int addRead(Long adId,Integer clickCount) {
        return addAdv.addAdvRead(adId,clickCount);
    }

    @Override
    public List<Adv> findSpecificAdv(Long adPositionId, Long adId, String adName) {
        return addAdv.findSpecificAdv(adPositionId, adId, adName);
    }
}
