package com.easyui.service.impl;

import com.easyui.dao.UserMapper;
import com.easyui.entity.User;
import com.easyui.entity.UserPO;
import com.easyui.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public Map<String, Object> getUsers(UserPO userPO) {
        PageHelper.startPage(userPO.getPage(), userPO.getRows());
        List<User>  users = userMapper.getUsers(userPO);
        Map<String, Object> map = new HashMap<>();
        PageInfo pageInfo = new PageInfo(users);
        map.put("rows", pageInfo.getList());
        System.out.println(users.size());
        map.put("total", pageInfo.getTotal());
        return map;
    }

    @Override
    public Integer addUser(User user) throws Exception {
        Integer addRows = userMapper.insertSelective(user);
        return addRows;
    }

    @Override
    public Integer modifiedUser(User user) throws Exception {
        Integer modifiedRows = userMapper.updateByPrimaryKey(user);
        return modifiedRows;
    }

    @Override
    public User getUser(User user) {
        return userMapper.selectByPrimaryKey(user);
    }

    @Override
    public Integer DelelteUser(int[] ids) throws Exception {
        return userMapper.deleteById(ids);
    }
}
