package com.ch.springsecuritydemo.controller;

import com.ch.springsecuritydemo.entity.User;
import com.ch.springsecuritydemo.result.Result;
import com.ch.springsecuritydemo.service.LoginUserIService;
import com.ch.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class WebController {

    @Autowired
    private LoginUserIService loginUserIService;

    @GetMapping("/say")
    public Result  SayHello(){
        return Result.success("Hello World");
    }


    @PostMapping("/user/login")
    public Result login(@RequestBody User user){
        Map<String, String> login = loginUserIService.login(user);
        return Result.success(login);
    }

    @DeleteMapping ("/user/logout")
    public Result logout(@RequestBody User user){
        Map<String, String> login = loginUserIService.logout(user);
        return Result.success(login);
    }
}
