package com.example.myproject.mapper;

import com.example.myproject.pojo.GroupRelationUser;

public interface GroupRelationUserMapper {

    int addGroupRelation(GroupRelationUser groupRelationUser);

    int deleteGroupRelation(Long relationId);
}
