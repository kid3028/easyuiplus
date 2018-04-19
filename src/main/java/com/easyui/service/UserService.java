package com.easyui.service;

import com.easyui.entity.User;
import com.easyui.entity.UserPO;
import com.github.pagehelper.PageInfo;

import java.sql.SQLException;
import java.util.Map;


public interface UserService {
    Map<String, Object> getUsers(UserPO userPO);

    Integer addUser(User user) throws Exception;

    Integer modifiedUser(User user) throws Exception;

    User getUser(User user);

    Integer DelelteUser(int[] ids) throws Exception;
}
