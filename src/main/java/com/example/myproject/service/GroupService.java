package com.example.myproject.service;

import com.example.myproject.pojo.Group;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface GroupService {

    int addGroup(Group group);

    int deleteGroup(Long groupId);

    int modifyGroup(Group group, Long groupId);

    List<Group> findGroupByIdOrName(Long groupId, String groupName);

    List<Group> findGroupDetails(Long groupId, String groupName);

    List<Group> finGroupByUserId(Long userId);

}
