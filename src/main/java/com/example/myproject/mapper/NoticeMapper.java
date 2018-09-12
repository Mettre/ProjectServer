package com.example.myproject.mapper;

import com.example.myproject.pojo.Notice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NoticeMapper {

    int addNotice(@Param(value = "notice") Notice notice);

    int sendNotice(@Param(value = "noticeId") Long noticeId, @Param(value = "noticeGroup") Long noticeGroup, @Param(value = "userId") Long userId, @Param(value = "allUser") Boolean allUser);

    List<Notice> noticeList(@Param(value = "groupIds") List<Long> groupIds, @Param(value = "userId") Long userId);
}
