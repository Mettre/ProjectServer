package com.example.myproject.mapper;

import com.example.myproject.pojo.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {

    int addNotice(@Param(value = "notice") Notice notice, @Param(value = "groupId") Integer groupId, @Param(value = "userId") Integer userIds, @Param(value = "allUser") Boolean allUser);

}
