package com.ssm.blog.service.impl;

import com.ssm.blog.entity.ArticleCategoryRef;
import com.ssm.blog.entity.Category;
import com.ssm.blog.mapper.ArticleCategoryRefMapper;
import com.ssm.blog.mapper.CategoryMapper;
import com.ssm.blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/3 17:00
 */
@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
   @Autowired
   private CategoryMapper categoryMapper;

   @Autowired
   private ArticleCategoryRefMapper articleCategoryRefMapper;

   @Override
    public Integer countCategory() {
       Integer count = 0;
       try {
           count = categoryMapper.countCategory();
       } catch (Exception e) {
           e.printStackTrace();
           log.error("count category error, cause:{}", e);
       }
       return count;
    }

    @Override
    public List<Category> listCategory() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryMapper.listCategory();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list category error, cause:{}", e);
        }
        return categoryList;
    }

    @Override
    public List<Category> listCategoryWithCount() {
        List<Category> categoryList = null;
        try {
            categoryList = categoryMapper.listCategory();
            for (int i = 0; i < categoryList.size(); i++) {
               Integer count= articleCategoryRefMapper.countArticleByCategoryId(categoryList.get(i).getCategoryId());
               categoryList.get(i).setArticleCount(count);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list category error, cause:{}", e);
        }
        return categoryList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCategory(Integer id) {
        try {
            categoryMapper.deleteCategory(id);
            articleCategoryRefMapper.deleteByCategoryId(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("delete category error, id:{}, cause:{}", id, e);
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    public Category getCategoryById(Integer id) {
       Category category=null;
        try {
           category= categoryMapper.getCategoryById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get category by id,id:{},cause:{}",id,e);
        }
        return category;
    }

    @Override
    public Category insertCategory(Category category) {
        try {
            categoryMapper.insert(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("insert category error,category:{},cause:{}",category,e);
        }
        return category;
    }

    @Override
    public void updateCategory(Category category) {
        try {
            categoryMapper.update(category);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("update category error,category:{},cause:{}",category,e);
        }
    }

    @Override
    public Category getCategoryByName(String name) {
        Category category = null;
        try {
            category = categoryMapper.getCategoryByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get category by name error, name:{}, cause:{}", name, e);
        }
        return category;
    }
}
