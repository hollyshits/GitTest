package com.starry.web;

import com.starry.entity.Users;
import com.starry.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UsersController {
    @Autowired
    private UserService service;

//@RequestMapping("/find/{id}")
//    public User findOneById(@PathVariable(value = "id")int id){
//        return service.findOneById(id);
//    }
@RequestMapping(value = "/login", method= RequestMethod.POST)
    public Users login(Users user){
    System.out.println("======来到登录控制器");
    System.out.println(user);
    Users user1 = service.login(user.getUsername(),user.getPassword());
    if(user1 == null){
        System.out.println("error!");
        user.setStatus(false);
        return user;
    }else {
        System.out.println("success!!");
        user.setId(user1.getId());
        user.setStatus(true);
        return user;
    }
}
@RequestMapping(value = "/register",method = RequestMethod.POST)
public boolean register(String username,String password){
    System.out.println("==========来到注册的方法=========");
    return service.register(username,password);
}


}
