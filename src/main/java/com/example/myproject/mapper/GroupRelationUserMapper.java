package com.example.myproject.mapper;

import org.apache.ibatis.annotations.Param;

public interface GroupRelationUserMapper {

    int addGroupRelation(@Param(value = "groupId") Long groupId, @Param(value = "userId") String userId);

    int deleteGroupRelation(Long relationId);
}
