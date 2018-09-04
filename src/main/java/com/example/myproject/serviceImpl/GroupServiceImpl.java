package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.GroupMapper;
import com.example.myproject.pojo.Group;
import com.example.myproject.service.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public int addGroup(Group group) {
        return groupMapper.addGroup(group);
    }

    @Override
    public int deleteGroup(Long groupId) {
        return groupMapper.deleteGroup(groupId);
    }

    @Override
    public List<Group> findGroupByNameOrName(Long groupId, String groupName) {
        return groupMapper.findGroupByNameOrName(groupId, groupName);
    }
}
