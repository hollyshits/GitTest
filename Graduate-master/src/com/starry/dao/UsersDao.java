package com.starry.dao;

import com.starry.entity.Users;
import org.apache.ibatis.annotations.Param;

public interface UsersDao {

    Users findOndeById(@Param("username") String username, @Param("password") String password);

    void addUser(@Param("username") String username, @Param("password") String password);

}
