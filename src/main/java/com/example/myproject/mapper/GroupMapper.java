package com.example.myproject.mapper;

import com.example.myproject.pojo.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {

    int addGroup(Group group);

    int deleteGroup(Long groupId);

    int modifyGroup(@Param(value = "group") Group group, @Param(value = "groupId") Long groupId);

    List<Group> findGroupByNameOrName(@Param(value = "groupId") Long groupId, @Param(value = "groupName") String groupName);

    List<Group> findGroupDetails(@Param(value = "groupId") Long groupId, @Param(value = "groupName") String groupName);

    List<Group> finGroupByUserId(Long userId);
}
