package com.example.myproject.service;

import com.example.myproject.pojo.Notice;

public interface NoticeService {

    int addNotice(Notice notice);

    int sendNotice(Long noticeId, Integer noticeGroup, Long userId, Boolean allUser);
}