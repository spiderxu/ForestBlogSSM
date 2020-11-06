package com.ssm.blog.controller.admin;

import com.ssm.blog.entity.Article;
import com.ssm.blog.entity.Category;
import com.ssm.blog.service.ArticleService;
import com.ssm.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/6 10:44
 * 后台文章分类控制器
 */
@Controller
@RequestMapping("/admin/category")
public class BackCategoryController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CategoryService categoryService;

    /**
     * 后台分类列表展示
     * @param model
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String categoryList(Model model){
        List<Category> categoryList = categoryService.listCategoryWithCount();
        model.addAttribute("categoryList",categoryList);
        return "/Admin/Category/index";
    }

    /**
     * 添加分类
     * @param category
     * @return
     */
    @RequestMapping(value = "insertSubmit",method = RequestMethod.POST)
    public String insertCategorySubmit(Category category){
        categoryService.insertCategory(category);
        return "redirect:/admin/category";
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteCategory(@PathVariable("id") Integer id){
        //如果该分类下有文章，则该分类不会被删除
        Integer count = articleService.countArticleByCategoryId(id);

        if (count==0){
            categoryService.deleteCategory(id);
        }
        return "redirect:/admin/category";
    }

    /**
     * 跳转到编辑分类页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String editCategoryView(@PathVariable("id") Integer id,Model model){
        Category category = categoryService.getCategoryById(id);
        model.addAttribute("category",category);

        List<Category> categoryList = categoryService.listCategoryWithCount();
        model.addAttribute("categoryList",categoryList);

        return "/Admin/Category/edit";
    }

    /**
     * 编辑分类后提交
     * @param category
     * @return
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editCategorySubmit(Category category){
        categoryService.updateCategory(category);
        return "redirect:/admin/category";
    }
}
