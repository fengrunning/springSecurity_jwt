package com.ch.springsecuritydemo.service.imp;

import com.ch.springsecuritydemo.entity.LoginUser;
import com.ch.springsecuritydemo.entity.User;
import com.ch.springsecuritydemo.exception.UserException;
import com.ch.springsecuritydemo.properties.JwtProperties;
import com.ch.springsecuritydemo.service.LoginUserIService;
import com.ch.springsecuritydemo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
@Service
public class LoginUserIServiceImp  implements LoginUserIService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public Map<String,String> login(User user) {
        try {
            //用户认证 调用 loadUserByUsername获取
            UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            if(Objects.isNull(authenticate)){
                throw  new UserException(602,"认证失败");
            }
            LoginUser loginUser =(LoginUser) authenticate.getPrincipal();
            //成功后  设置 token
            Map<String, Object> claims=new HashMap<>();
            // 这里存入 类
            claims.put("userId",1);
            String token= JwtUtil.createJWT(jwtProperties.getUserSecretKey(),jwtProperties.getUserTtl(),claims);
            Map<String,String> map=new HashMap<>();
            map.put("token",token);
            return  map;
        } catch (BadCredentialsException e) {
            throw new UserException(602, "用户名或密码错误");
        } catch (DisabledException e) {
            throw new UserException(603, "用户被禁用");
        } catch (Exception e) {
            throw new UserException(604, "认证失败");
        }
    }

    @Override
    public Map<String, String> logout(User user) {
        // 1 删除 context 和缓存
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long id = loginUser.getUser().getId();
        Map<String,String> mp   = new HashMap<>();
        // 根据id删除数据
        mp.put("msg","删除成功");
        return mp;
    }

}
