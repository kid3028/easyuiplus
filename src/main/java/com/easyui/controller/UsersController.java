package com.easyui.controller;

import com.easyui.entity.User;
import com.easyui.entity.UserPO;
import com.easyui.service.UserService;
import com.github.pagehelper.PageInfo;
import com.sun.corba.se.impl.orb.ParserTable;
import com.sun.imageio.plugins.common.BitFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.sql.SQLException;
import java.util.*;


@Controller
@RequestMapping("/user")
public class UsersController {
    @Autowired
    private UserService userService;


    @RequestMapping("")
    public String loginUI() {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Boolean login(User user) {
        System.out.println(user.getPassword() + "   " + user.getUserName());
        if (user.getUserName() == null || user.getPassword() == null || user.getUserName().length() == 0 || user.getPassword().length() == 0) {
            return false;
        }

        if ("admin".equals(user.getUserName()) && "123456".equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping("/dataGrid")
    public String dataGrid() {
        return "main/center";
    }

    @RequestMapping("/userInfo")
    @ResponseBody
    public Map<String, Object> userInfo(UserPO userPO) {
        Map<String, Object> map = userService.getUsers(userPO);
        return map;



    }


    @RequestMapping("/addUser")
    @ResponseBody
    public Integer addUser(User user) {
        if(user != null) {
            try {
                Integer addRows = userService.addUser(user);
                return addRows;
            } catch (Exception e) {
                System.out.println("数据库异常");
                return -1;
            }
        }
        return -1;
    }


    @RequestMapping("/modifiedUser")
    @ResponseBody
    public Integer updateUser(User user){
        if(user != null) {
            User dbUser = userService.getUser(user);
            if (dbUser == null) {
                return -1;
            }
            try {
                Integer modifiedRows = userService.modifiedUser(user);
                return modifiedRows;
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;
    }


    @RequestMapping("/deleteUser")
    @ResponseBody
    public Integer deleteUser(@RequestParam("ids[]") int[] ids) {
        if(ids != null) {
            try {
                Integer deleteRows = userService.DelelteUser(ids);
                return deleteRows;
            } catch (Exception e) {
                return -1;
            }
        }
        return -1;

    }


}

