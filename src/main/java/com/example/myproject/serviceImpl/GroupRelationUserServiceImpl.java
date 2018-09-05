package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.GroupRelationUserMapper;
import com.example.myproject.pojo.GroupRelationUser;
import com.example.myproject.service.GroupRelationUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Slf4j
@Service
@Transactional
public class GroupRelationUserServiceImpl implements GroupRelationUserService {

    @Autowired
    private GroupRelationUserMapper groupRelationUserMapper;


    @Override
    public int addGroupRelation(GroupRelationUser groupRelationUser) {
        return groupRelationUserMapper.addGroupRelation(groupRelationUser);
    }

    @Override
    public int deleteGroupRelation(Long relationId) {
        return groupRelationUserMapper.deleteGroupRelation(relationId);
    }
}
