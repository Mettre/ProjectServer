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
    public int modifyGroup(Group group, Long groupId) {
        return groupMapper.modifyGroup(group, groupId);
    }

    @Override
    public List<Group> findGroupByIdOrName(Long groupId, String groupName) {
        return groupMapper.findGroupByNameOrName(groupId, groupName);
    }

    @Override
    public List<Group> findGroupDetails(Long groupId, String groupName) {
        return groupMapper.findGroupDetails(groupId, groupName);
    }

    @Override
    public List<Group> finGroupByUserId(Long userId) {
        return groupMapper.finGroupByUserId(userId);
    }
}
