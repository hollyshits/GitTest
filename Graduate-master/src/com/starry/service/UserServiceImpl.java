package com.starry.service;

import com.starry.dao.UsersDao;
import com.starry.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UsersDao mapper;


    @Override
    public Users login(String username, String password) {
        return mapper.findOndeById(username,password);
    }

    @Override
    public boolean register(String username, String password) {
        try{
            mapper.addUser(username,password);
            return true;
        }catch (Exception e){
            System.out.println("注册失败！！");
            return false;
        }

    }
}
