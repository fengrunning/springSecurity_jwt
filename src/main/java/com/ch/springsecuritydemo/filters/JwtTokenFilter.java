package com.ch.springsecuritydemo.filters;

import com.ch.springsecuritydemo.entity.LoginUser;
import com.ch.springsecuritydemo.entity.User;
import com.ch.springsecuritydemo.exception.UserException;
import com.ch.springsecuritydemo.properties.JwtProperties;
import com.ch.springsecuritydemo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtProperties jwtProperties;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token =request.getHeader("token");
        if(!StringUtils.hasLength(token)){
            filterChain.doFilter(request,response);
            return;
        }
        String userId;
        try {
            Claims claims= JwtUtil.parseJWT(jwtProperties.getUserSecretKey(),token);
            userId =claims.getSubject();
        }
        catch (Exception e){
            e.printStackTrace();
            throw new UserException(401,"token解析失败");
        }
//        获取 user对象
        User user = User.builder().username("test").password("$2a$10$4u/4EYVopqOzFxYKwWcxKebfPXMW5y2tADwSxPnTlXJfs6Q/RQQ4q").build();
        LoginUser loginUser = new LoginUser(user);
        //一般从 redis 获取对象 存入 SecurityContextHolder中
        if(Objects.nonNull(loginUser)){
            UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(loginUser,null,null);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
