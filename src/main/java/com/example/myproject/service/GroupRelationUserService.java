package com.example.myproject.service;

import com.example.myproject.pojo.GroupRelationUser;

public interface GroupRelationUserService {

    int addGroupRelation(GroupRelationUser groupRelationUser);

    int deleteGroupRelation(Long relationId);
}
