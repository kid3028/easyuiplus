package com.easyui.controller;

import com.easyui.entity.Menu;
import com.easyui.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @RequestMapping("")
    public String getMenu(){
        return "main/west";
    }

    @RequestMapping("/getMenuByPid")
    @ResponseBody
    public List<Menu> getMenuByPid(@RequestParam(name = "id",required = false) Integer pid){
        try {
            List<Menu> menus = menuService.getMenuByPid(pid);
            System.out.println("success");
            return menus;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("fail");
            return  null;
        }
    }
}
