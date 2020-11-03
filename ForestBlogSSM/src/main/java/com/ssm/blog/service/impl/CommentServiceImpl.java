package com.ssm.blog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.blog.entity.Article;
import com.ssm.blog.entity.Comment;
import com.ssm.blog.entity.Page;
import com.ssm.blog.enums.ArticleStatus;
import com.ssm.blog.mapper.ArticleMapper;
import com.ssm.blog.mapper.CommentMapper;
import com.ssm.blog.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/3 16:31
 */
@Service
@Slf4j
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void insertComment(Comment comment) {
        try {
            commentMapper.insert(comment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("insert comment error,comment:{},cause:{}",comment,e);
        }

    }

    @Override
    public List<Comment> listCommentByArticleId(Integer articleId) {
        List<Comment> commentList=null;
        try {
           commentList= commentMapper.listCommentByArticleId(articleId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list comment by articleId error,articleId:{},cause:{}",articleId,e);
        }
        return commentList;
    }

    @Override
    public Comment getCommentById(Integer id) {
        Comment comment=null;
        try {
            comment=commentMapper.getCommentById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get comment by id error,id:{},cause:{}",id,e);
        }
        return comment;
    }

    @Override
    public PageInfo<Comment> listCommentByPage(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Comment> commentList=null;

        try {
            commentList=commentMapper.listComment();
            for (int i = 0; i < commentList.size(); i++) {
                Article article = articleMapper.getArticleByStatusAndId(ArticleStatus.PUBLISH.getValue(), commentList.get(i).getCommentArticleId());
               commentList.get(i).setArticle(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list comment by page ,pageIndex:{},pageSize:{},cause:{}",pageIndex,pageSize,e);
        }

        return new PageInfo<>(commentList);
    }

    @Override
    public List<Comment> listComment() {
        List<Comment> commentList=null;
        try {
            commentList=commentMapper.listComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get comment error");
        }
        return commentList;
    }

    @Override
    public void deleteComment(Integer id) {
        try {
            commentMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("delete comment error,id:{},cause:{}",id,e);
        }

    }

    @Override
    public void updateComment(Comment comment) {
        try {
            commentMapper.update(comment);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("update comment error,comment:{},cause:{}",comment,e);
        }
    }

    @Override
    public Integer countComment() {
        Integer commentCount=null;
        try {
           commentCount= commentMapper.countComment();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("count comment error,cause:{}",e);
        }
        return commentCount;
    }

    @Override
    public List<Comment> listRecentComment(Integer limit) {
        List<Comment> commentList=null;
        try {
            commentList=commentMapper.listRecentComment(limit);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list recent comment,limit:{},cause:{}",limit,e);
        }
        return commentList;
    }

    @Override
    public List<Comment> listChildComment(Integer id) {
        List<Comment> childCommentList=null;
        try {
           childCommentList= commentMapper.listChildComment(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get child comment error,id:{},cause:{}",id,e);
        }
        return childCommentList;
    }
}
