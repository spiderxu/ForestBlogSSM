package com.ssm.blog.controller.admin;

import com.ssm.blog.entity.Tag;
import com.ssm.blog.service.ArticleService;
import com.ssm.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/6 10:49
 */
@Controller
@RequestMapping("/admin/tag")
public class BackTagController {
    @Autowired
    private ArticleService articleService;

    @Autowired
    private TagService tagService;

    /**
     * 后台标签列表显示
     * @param model
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public String tagList(Model model){
        List<Tag> tagList = tagService.listTagWithCount();
        model.addAttribute("tagList",tagList);
        return "/Admin/Tag/index";
    }

    /**
     * 添加标签
     * @param tag
     * @return
     */
    @RequestMapping(value = "/insertSubmit",method = RequestMethod.POST)
    public String insertTagSubmit(Tag tag){
        tagService.insertTag(tag);
        return "redirect:/admin/tag";
    }

    /**
     * 删除标签
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public String deleteTag(@PathVariable("id") Integer id){
        //如果该标签下有文章，不允许删除
        Integer count = articleService.countArticleByCategoryId(id);

        if (count==0){
            tagService.deleteTag(id);
        }
        return "redirect:/admin/tag";
    }

    /**
     * 跳转到编辑标签页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String editTagView(@PathVariable("id") Integer id,Model model){
        Tag tag = tagService.getTagById(id);
        model.addAttribute("tag",tag);

        List<Tag> tagList = tagService.listTagWithCount();
        model.addAttribute("tagList",tagList);

        return "/Admin/Tag/edit";
    }

    /**
     * 编辑标签后提交
     * @param tag
     * @return
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editTagSubmit(Tag tag){
        tagService.updateTag(tag);
        return "redirect:/admin/tag";
    }
}
