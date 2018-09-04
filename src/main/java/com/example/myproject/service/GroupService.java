package com.example.myproject.service;

import com.example.myproject.pojo.Group;

import java.util.List;

public interface GroupService {

    int addGroup(Group group);

    int deleteGroup(Long groupId);

    List<Group> findGroupByNameOrName(Long groupId, String groupName);
}
