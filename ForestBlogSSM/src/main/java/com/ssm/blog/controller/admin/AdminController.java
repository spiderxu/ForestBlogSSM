package com.ssm.blog.controller.admin;

import com.ssm.blog.entity.Article;
import com.ssm.blog.entity.Comment;
import com.ssm.blog.entity.User;
import com.ssm.blog.service.ArticleService;
import com.ssm.blog.service.CommentService;
import com.ssm.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.ssm.blog.util.MyUtils.getIpAddr;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: xuzhi
 * @date: 2020/11/5 18:02
 * 后台用户控制器
 */
@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/loginVerify",method = RequestMethod.POST)
    @ResponseBody
    public String loginVerify(HttpServletRequest request, HttpServletResponse response){
        Map<String,Object> map=new HashMap<>();
        //获取前端表单传过来的数据
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String rememberme = request.getParameter("rememberme");

        //查询该用户是否存在
         User user = userService.getUserByNameOrEmail(username);

         if (user==null){
             map.put("code",0);
             map.put("msg","用户名无效");
         }else if (!user.getUserPass().equals(password)){
             map.put("code",0);
             map.put("msg","密码错误！");
         }else{
             //登录成功
             map.put("code",1);
             map.put("msg","");

             //添加session
             request.getSession().setAttribute("user",user);
             //添加cookie
             if (rememberme!=null){
                 Cookie nameCookie=new Cookie("username",username);
                 nameCookie.setMaxAge(60*60*24*3);

                 Cookie pwdCookie=new Cookie("password",password);
                 pwdCookie.setMaxAge(60*60*24*3);

                 response.addCookie(nameCookie);
                 response.addCookie(pwdCookie);

                 user.setUserLastLoginTime(new Date());
                 user.setUserLastLoginIp(getIpAddr(request));
                 userService.updateUser(user);
             }
         }

         //问题
         return username;

    }


    /**
     * 登录页面显示
     * @return
     */
    @RequestMapping(value = "/login")
    public String loginPage(){
        return "Admin/login";
    }

    /**
     * 后台首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/admin")
    public  String index(Model model){
        //最近五篇文章
        List<Article> articleList=articleService.listRecentArticle(5);
        model.addAttribute("articleList",articleList);

        //最新评论
        List<Comment> commentList=commentService.listRecentComment(5);
        model.addAttribute("commentList",commentList);

        return "admin/index";

    }



}
