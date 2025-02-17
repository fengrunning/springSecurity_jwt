package com.ch.springsecuritydemo.service;

import com.ch.springsecuritydemo.entity.User;

import java.util.Map;

public interface LoginUserIService {


    Map<String,String> login(User user);

    Map<String, String> logout(User user);
}
