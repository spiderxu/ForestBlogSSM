package com.ssm.blog.service.impl;

import com.ssm.blog.entity.Link;
import com.ssm.blog.mapper.LinkMapper;
import com.ssm.blog.service.LinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/3 16:20
 */
@Service
@Slf4j
public class LinkServiceImpl implements LinkService {

    @Autowired
    private LinkMapper linkMapper;

    @Override
    public Integer countLink(Integer status) {
        Integer count=null;
        try {
           count= linkMapper.countLink(status);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("count link error,status:{},cause:{}",status,e);
        }
        return count;
    }

    @Override
    public List<Link> listLink(Integer status) {
        List<Link> linkList=null;
        try {
            linkList=linkMapper.listLink(status);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list link error,status:{},cause:{}",status,e);
        }
        return linkList;
    }

    @Override
    public void insertLink(Link link) {

        try {
            linkMapper.insert(link);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("insert link error,link:{},cause:{}",link,e);
        }

    }

    @Override
    public void deleteLink(Integer id) {
        try {
            linkMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("delete link error,id:{},cause:{}",id,e);
        }

    }

    @Override
    public void updateLink(Link link) {
        try {
            linkMapper.update(link);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("update link error,link:{}",link,e);
        }
    }

    @Override
    public Link getLinkById(Integer id) {
        Link link=null;
        try {
            link = linkMapper.getLinkById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get link by id error,id:{},cause:{}",id,e);
        }
        return link;
    }
}
