package com.easyui.service.impl;

import com.easyui.dao.MenuMapper;
import com.easyui.entity.Menu;
import com.easyui.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> getMenuByPid(Integer pid) throws Exception {
        return menuMapper.getMenuByPid(pid);
    }
}
