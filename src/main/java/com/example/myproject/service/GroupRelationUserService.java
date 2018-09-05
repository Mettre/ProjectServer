package com.example.myproject.service;

public interface GroupRelationUserService {

    int addGroupRelation(Long groupId,String userId);

    int deleteGroupRelation(Long relationId);
}
