package com.ssm.blog.controller.admin;

import com.ssm.blog.entity.Page;
import com.ssm.blog.enums.PageStatus;
import com.ssm.blog.service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/6 15:12
 * 后台页面控制器
 */
@Controller
@RequestMapping("/admin/page")
public class BackPageController {
    @Autowired
    private PageService pageService;

    /**
     * 后台页面列表展示
     * @param model
     * @return
     */
    @RequestMapping(value = "")
    public String index(Model model){
        List<Page> pageList = pageService.listPage(null);
        model.addAttribute("pageList",pageList);
        return "/Admin/Page/index";
    }

    /**
     * 自定义页面删除
     * @param id
     * @return
     */
    @RequestMapping(value = "/delete/{id}")
    public String deletePage(@PathVariable("id") Integer id){
        pageService.deletePage(id);
        return "redirect:/admin/page";
    }

    /**
     * 跳转到编辑页面
     * @param id
     * @return
     */
    @RequestMapping(value = "/edit/{id}")
    public String editPageView(@PathVariable("id") Integer id,Model model){
        Page page = pageService.getPageById(id);
        model.addAttribute("page",page);
        return "/Admin/Page/edit";
    }

    /**
     * 编辑页面提交
     * @param page
     * @return
     */
    @RequestMapping(value = "/editSubmit",method = RequestMethod.POST)
    public String editPageSubmit(Page page){
        Page checkPage = pageService.getPageByKey(null, page.getPageKey());
        if (page.getPageId().equals(checkPage.getPageId())){
            page.setPageUpdateTime(new Date());
            pageService.updatePage(page);
        }
        return "redirect:/admin/page";
    }

    /**
     * 跳转到新增页面
     * @return
     */
    @RequestMapping(value = "/insert")
    public String insertPageView(){
        return "Admin/Page/insert";
    }

    /**
     * 新增页面提交
     * @param page
     * @return
     */
    @RequestMapping(value = "insertSubmit",method = RequestMethod.POST)
    public String insertPageSubmit(Page page){
        Page checkPage = pageService.getPageByKey(null, page.getPageKey());
        if (checkPage==null){
            page.setPageCreateTime(new Date());
            page.setPageUpdateTime(new Date());
            page.setPageStatus(PageStatus.NORMAL.getValue());
            pageService.insertPage(page);
        }
        return "redirect:/admin/page";
    }
}
