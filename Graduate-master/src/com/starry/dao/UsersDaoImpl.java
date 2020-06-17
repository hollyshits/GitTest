package com.starry.dao;

import com.starry.entity.Users;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.HashMap;

public class UsersDaoImpl extends SqlSessionDaoSupport implements UsersDao {
    @Override
    public Users findOndeById(String username, String password) {
        HashMap<String,String> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        return this.getSqlSession().selectOne("findOndeById",map);
    }

    @Override
    public void addUser(String username, String password) {
        HashMap<String,String> map=new HashMap<>();
        map.put("username",username);
        map.put("password",password);
        this.getSqlSession().insert("addUser",map);
    }
}
