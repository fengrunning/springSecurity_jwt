package com.ch.springsecuritydemo.service.imp;

import com.ch.springsecuritydemo.entity.User;
import com.ch.springsecuritydemo.entity.LoginUser;
import com.ch.springsecuritydemo.exception.UserException;
import com.ch.springsecuritydemo.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class UserServiceImp implements UserDetailsService,UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //todo:mysql查询用户信息 未加密
        // todo:查询权限
        if(!"test".equals(username)){
            throw new  UserException(601,"没有账号");
        }
        //假加密
        User user = User.builder().username("test").password("$2a$10$4u/4EYVopqOzFxYKwWcxKebfPXMW5y2tADwSxPnTlXJfs6Q/RQQ4q").build();
        return new LoginUser(user);
    }


}
