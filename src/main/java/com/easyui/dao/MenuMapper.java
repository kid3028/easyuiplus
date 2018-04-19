package com.easyui.dao;

import com.easyui.entity.Menu;
import com.mybatis.MyMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuMapper extends MyMapper<Menu> {
//    List<Menu> getMenuByPid(@Param("pid") Integer pid);
    List<Menu> getMenuByPid(Integer pid);
}