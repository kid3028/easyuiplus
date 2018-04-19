package com.easyui.dao;

import com.easyui.entity.User;
import com.easyui.entity.UserPO;
import com.mybatis.MyMapper;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
    List<User> getUsers(UserPO userPO);

    Integer deleteById(int[] ids);
}