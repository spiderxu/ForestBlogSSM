package com.ssm.blog.service.impl;

import com.ssm.blog.entity.Menu;
import com.ssm.blog.mapper.MenuMapper;
import com.ssm.blog.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xuzhi
 * @date: 2020/11/3 16:13
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> listMenu() {
        List<Menu> menuList=null;
        try {
            menuList=menuMapper.listMenu();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("list menu is error,cause:{}",e);
        }
        return menuList;
    }

    @Override
    public Menu insertMenu(Menu menu) {
        try {
            menuMapper.insert(menu);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("insert menu error,menu:{},cause:{}",menu,e);
        }
        return menu;
    }

    @Override
    public void deleteMenu(Integer id) {
        try {
            menuMapper.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("delete menu error,id:{},cause:{}",id,e);
        }

    }

    @Override
    public void updateMenu(Menu menu) {
        try {
            menuMapper.update(menu);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("update menu error,menu:{},cause:{}",menu,e);
        }
    }

    @Override
    public Menu getMenuById(Integer id) {
        Menu menu=null;
        try {
           menu= menuMapper.getMenuById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("get menu by id error,id:{},cause:{}",id,e);
        }
        return menu;
    }
}
