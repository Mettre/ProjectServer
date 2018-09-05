package com.example.myproject.mapper;

import com.example.myproject.pojo.Notice;
import org.apache.ibatis.annotations.Param;

public interface NoticeMapper {

    int addNotice(@Param(value = "notice") Notice notice);

    int sendNotice(@Param(value = "noticeId") Long noticeId, @Param(value = "noticeGroup") Integer noticeGroup, @Param(value = "userId") Long userId, @Param(value = "allUser") Boolean allUser);


}
