package com.ssm.blog.service.impl;

import com.ssm.blog.entity.Notice;
import com.ssm.blog.mapper.NoticeMapper;
import com.ssm.blog.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/3 15:52
 */
@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> listNotice(Integer status) {
        List<Notice> noticeList=null;
        try {
            noticeList=noticeMapper.listNotice(status);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list notice error,status:{},cause:{}",status,e);
        }
        return noticeList;
    }

    @Override
    public void insertNotice(Notice notice) {
        try {
            noticeMapper.insert(notice);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("notice insert error,notice:{},cause:{}",notice,e);
        }
    }

    @Override
    public void deleteNotice(Integer id) {
        try {
            noticeMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("notice delete error,id:{}cause:{}",id,e);
        }

    }

    @Override
    public void updateNotice(Notice notice) {
        try {
            noticeMapper.update(notice);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("notice update error,notice:{},cause:{}",notice,e);
        }
    }

    @Override
    public Notice getNoticeById(Integer id) {
        Notice notice=null;
        try {
            notice=noticeMapper.getNoticeById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get notice by id error,id:{},cause:{}",id,e);
        }
        return notice;
    }
}
