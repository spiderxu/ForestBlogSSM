package com.ssm.blog.service.impl;

import com.ssm.blog.entity.Page;
import com.ssm.blog.mapper.PageMapper;
import com.ssm.blog.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/3 15:37
 */
@Service
@Slf4j
public class PageServiceImpl implements PageService {
    @Autowired
    private PageMapper pageMapper;

    @Override
    public List<Page> listPage(Integer status) {
        List<Page> pageList=null;
        try {
            pageList = pageMapper.listPage(status);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("listPage error, status:{},e:{}",status,e);
        }
        return pageList;
    }

    @Override
    public Page getPageByKey(Integer status, String key) {
        Page page=null;

        try {
            page=pageMapper.getPageByKey(status,key);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(" get page by key error,status:{},key:{},cause:{}",status,key,e);
        }
        return page;
    }

    @Override
    public Page getPageById(Integer id) {
        Page page=null;
        try {
            page=pageMapper.getPageById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(" get page by id error,id{},cause:{}",id,e);
        }
        return page;
    }

    @Override
    public void insertPage(Page page) {
        try {
            pageMapper.insert(page);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("page insert error,page:{},cause:{}",page,e);
        }

    }

    @Override
    public void deletePage(Integer id) {
        try {
            pageMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("page delete error,id:{},cause:{}",id,e);
        }
    }

    @Override
    public void updatePage(Page page) {
        try {
            pageMapper.update(page);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("page update error,id:{},cause:{}",page,e);
        }
    }
}
