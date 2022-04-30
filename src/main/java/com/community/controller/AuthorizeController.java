package com.community.controller;

import cn.hutool.http.HttpUtil;
import com.community.execption.ControllerExecption;
import com.community.enums.ErrorEnum;

import com.community.hander.ResultJson;
import com.community.model.User;
import com.community.services.UserService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class AuthorizeController {
    @Setter
    @Autowired
    private UserService userService;
    @PostMapping("/logIn")
    public ResultJson logIn(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody User user){
        System.out.println(user);
        ResultJson<User> json=new ResultJson<User>(0,"持久化登陆成功");
        Cookie[] cookies = request.getCookies();
        User login=null;
        Cookie cookie=new Cookie("token",null);
        if(cookies==null||cookies.length==1){
            login= userService.login(user);
            cookie.setValue(request.getSession().getId());
        }else{
            //根据token查找用户
            List<User> token = Arrays.stream(cookies).filter(e -> {
                return (e.getName().equals("token"));
            }).map(e -> {
                return userService.getUser(e.getValue());
            }).filter(e -> {
                return e != null;
            }).collect(Collectors.toList());
            System.out.println(1);
            if(token.size()==0){
                login=null;
            }else{
                login=token.get(0);
                cookie.setValue(login.getToken());
            }

        }
        if(login==null){
            json.failed(ErrorEnum.USER_NOT_FOUND);
            throw new ControllerExecption(ErrorEnum.USER_NOT_FOUND);
        }else{
            login.setToken(cookie.getValue());
            cookie.setMaxAge(60 * 60 * 24 * 7);
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println(login.toString());
            userService.updataUser(login);
        }
        return json;
    }
    @GetMapping("/logOut")
    public ResultJson<Object> logOut(
            HttpServletRequest request,
            HttpServletResponse response){
        request.getSession().invalidate();
        Cookie cookie = new Cookie("token", "");
//        cookie.setDomain(request.getRequestURL().toString());
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ResultJson<Object>(0,"登出成功");
    }

    @GetMapping("/checkLogin")
    public ResultJson<User> checkLog(
            HttpServletResponse response,
            HttpServletRequest request){
        String sessionId=request.getSession().getId();
        ResultJson<User> json=new ResultJson<>(0,"未登录",null,null);
        Arrays.stream(request.getCookies())
                .filter(cookie -> {
                    return cookie != null && "token".equals(cookie.getName());
                }).limit(1).forEach(cookie -> {
                    if(cookie!=null){
                        User user= userService.getUser(cookie.getValue());
                        if(user!=null) {
                            json.setMessage("以登录");
                            json.setCode(0);
                            json.setData(user);
                        }
                    }
                });
        return json;
    }
    @PutMapping("logOn")
    public ResultJson<User> logOn(@RequestBody User user){
            ResultJson json=new ResultJson("注册成功");
            userService.createUser(user);
            return json;
    }
    @GetMapping("/log")
    public void logGitee(HttpServletRequest request,HttpServletResponse response ) throws IOException {
        String url="https://gitee.com/oauth/authorize?client_id=533270564bc0c9d9e10c23c6f802f05e2534513612651c01d5692139536492e7&redirect_uri=http://localhost:8081/backCall&response_type=code";
        response.sendRedirect(url);
//        return null;
    }
    @GetMapping("/backCall")
    public ResultJson<User> logGitee(@RequestParam("code") String code){
        String tokenRegx="https://gitee.com/oauth/token?grant_type=authorization_code";
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",code);
        map.put("client_id","533270564bc0c9d9e10c23c6f802f05e2534513612651c01d5692139536492e7");
        map.put("redirect_uri","http://localhost:8081/backCall");
        map.put("client_secret","e13186384d6ff6c7b92c44fa610180cb215fca61ed50920431235355a5f00458");
//        System.out.println(tokenUrl);
        String token=HttpUtil.post(tokenRegx,map);
        System.out.println("token:"+token);
        String getUser="https://gitee.com/api/v5/user?access_token="+token;
        System.out.println(HttpUtil.get(getUser));
        return new ResultJson<>();
    }
}
