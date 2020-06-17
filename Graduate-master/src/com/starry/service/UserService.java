package com.starry.service;

import com.starry.entity.Users;

public interface UserService {

    Users login(String username, String password);
    boolean register(String username, String password);

}
