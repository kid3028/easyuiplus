package com.easyui.service;

import com.easyui.entity.Menu;

import java.sql.SQLException;
import java.util.List;

public interface MenuService {

    List<Menu> getMenuByPid(Integer pid) throws Exception;
}
