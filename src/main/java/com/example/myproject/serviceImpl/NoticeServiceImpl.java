package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.NoticeMapper;
import com.example.myproject.pojo.Group;
import com.example.myproject.pojo.Notice;
import com.example.myproject.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;


    @Override
    public int addNotice(Notice notice) {
        return noticeMapper.addNotice(notice);
    }

    @Override
    public int sendNotice(Long noticeId, Long noticeGroup, Long userId, Boolean allUser) {
        return noticeMapper.sendNotice(noticeId, noticeGroup, userId, allUser);
    }

    @Override
    public List<Notice> noticeList(List<Long> groupIds, Long userId) {
        return noticeMapper.noticeList(groupIds, userId);
    }
}
