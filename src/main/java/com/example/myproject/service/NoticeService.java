package com.example.myproject.service;

import com.example.myproject.pojo.Notice;

import java.util.List;

public interface NoticeService {

    int addNotice(Notice notice);

    int sendNotice(Long noticeId, Long noticeGroup, Long userId, Boolean allUser);

    List<Notice> noticeList(List<Long> groupIds, Long userId);

}
