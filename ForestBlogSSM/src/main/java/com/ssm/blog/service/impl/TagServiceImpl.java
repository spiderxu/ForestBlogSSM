package com.ssm.blog.service.impl;

import com.ssm.blog.entity.Tag;
import com.ssm.blog.mapper.ArticleTagRefMapper;
import com.ssm.blog.mapper.TagMapper;
import com.ssm.blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/3 15:12
 */

@Service
@Slf4j
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;

    @Autowired
    private ArticleTagRefMapper articleTagRefMapper;

    @Override
    public Integer countTag() {
        return tagMapper.countTag();
    }

    @Override
    public List<Tag> listTag() {
        List<Tag> tagList=null;
        try {
            tagList=tagMapper.listTag();
        }catch (Exception e){
            e.printStackTrace();
            log.error("list tag error，cause:{}",e);
        }

        return tagList;
    }

    @Override
    public List<Tag> listTagWithCount() {
        List<Tag> tagList=null;
        try {
            tagList=tagMapper.listTag();
            for (int i = 0; i < tagList.size(); i++) {
                int count = articleTagRefMapper.countArticleByTagId(tagList.get(i).getTagId());
                tagList.get(i).setArticleCount(count);
            }
        }catch (Exception e){
            e.printStackTrace();
            log.error("list tag with count error，cause:{}",e);
        }

        return tagList;
    }

    @Override
    public Tag getTagById(Integer id) {
        Tag tag=null;

        try {
            tag = tagMapper.getTagById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get tag by id error，id:{},cause:{}",id,e);
        }
        return tag;
    }

    @Override
    public Tag insertTag(Tag tag) {

        try {
            tagMapper.insert(tag);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("insert tag error，tag:{},cause:{}",tag,e);
        }
        return tag;
    }

    @Override
    public void updateTag(Tag tag) {
        try {
            tagMapper.insert(tag);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("update tag error，tag:{},cause:{}",tag,e);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteTag(Integer id) {
        /**
         * 由于我们要对两张表进行删除操作，必须保证事务
         */
        try {
            tagMapper.deleteById(id);
            articleTagRefMapper.deleteByTagId(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("delete tag error, id:{}, cause:{}", id, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public Tag getTagByName(String name) {
        Tag tag = null;
        try {
            tag = tagMapper.getTagByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get tag by name error, name:{}, cause:{}", name, e);
        }
        return tag;
    }

    @Override
    public List<Tag> listTagByArticleId(Integer articleId) {
        List<Tag> tagList = null;
        try {
            tagList = articleTagRefMapper.listTagByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get listTag by articleId error，articleId:{}, cause:{}", articleId, e);
        }
        return tagList;
    }
}
