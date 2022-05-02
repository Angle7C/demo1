package com.community.controller;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.community.Inter.Oauth;
import com.community.enums.SucessEnum;
import com.community.execption.ControllerExecption;
import com.community.enums.ErrorEnum;

import com.community.hander.ResultJson;
import com.community.model.User;
import com.community.oauthuser.GiteeOauth;
import com.community.oauthuser.GithubOauth;
import com.community.services.UserService;
import com.google.gson.JsonParser;
import lombok.Setter;
import lombok.Synchronized;
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
            @RequestBody User user) {
        System.out.println(user);
        ResultJson<User> json = new ResultJson<User>(0, "持久化登陆成功");
        Cookie[] cookies = request.getCookies();
        User login = null;
        Cookie cookie = new Cookie("token", null);
        if (cookies == null || cookies.length == 1) {
            login = userService.login(user);
            cookie.setValue(request.getSession().getId());
        } else {
            //根据token查找用户
            List<User> token = Arrays.stream(cookies).filter(e -> {
                return (e.getName().equals("token"));
            }).map(e -> {
                return userService.getUser(e.getValue());
            }).filter(e -> {
                return e != null;
            }).collect(Collectors.toList());
            System.out.println(1);
            if (token.size() == 0) {
                login = null;
            } else {
                login = token.get(0);
                cookie.setValue(login.getToken());
            }

        }
        if (login == null) {
            json.failed(ErrorEnum.USER_NOT_FOUND);
        } else {
            login.setToken(cookie.getValue());
            cookie.setMaxAge(60 * 60 * 24 * 7);
            cookie.setPath("/");
            response.addCookie(cookie);
            System.out.println(login);
            userService.updataOrCreateUser(login);
        }
        return json;
    }

    @GetMapping("/logOut")
    public ResultJson<Object> logOut(
            HttpServletRequest request,
            HttpServletResponse response) {
        request.getSession().invalidate();
        Cookie cookie = new Cookie("token", "");
//        cookie.setDomain(request.getRequestURL().toString());
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ResultJson<Object>(0, "登出成功");
    }

    @GetMapping("/checkLogin")
    public ResultJson<User> checkLog(
            HttpServletResponse response,
            HttpServletRequest request) {
        String sessionId = request.getSession().getId();
        ResultJson<User> json = new ResultJson<>(0, "未登录", null, null);
        Arrays.stream(request.getCookies())
                .filter(cookie -> {
                    return cookie != null && "token".equals(cookie.getName());
                }).limit(1).forEach(cookie -> {
                    if (cookie != null) {
                        User user = userService.getUser(cookie.getValue());
                        if (user != null) {
                            json.setMessage("以登录");
                            json.setCode(0);
                            json.setData(user);
                        }
                    }
                });
        return json;
    }

    @PutMapping("logOn")
    public ResultJson<User> logOn(@RequestBody User user) {
//            ResultJson json=new ResultJson("注册成功");
        return null;
    }

    @GetMapping("/log/{type}")
    public void log(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) throws IOException {
        String url=null;
        if(type.toLowerCase().equals("gitee")){
            url = "https://gitee.com/oauth/authorize?client_id=533270564bc0c9d9e10c23c6f802f05e2534513612651c01d5692139536492e7&redirect_uri=http://localhost:8081/backCall/" + type + "&response_type=code";
        }else if(type.toLowerCase().equals("github")){
            url="https://github.com/login/oauth/authorize?client_id=87d98f857294a395b413&redirect_uri=http://localhost:8081/backCall/Github";
        }else{
            url=null;
        }
        response.sendRedirect(url);
    }

    @GetMapping("/backCall/{type}")
    public ResultJson<User> logUser(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code, @PathVariable("type") String type) {
        ResultJson json = null;
        Oauth oauth = null;
        if (type.equalsIgnoreCase("gitee")) {
            oauth = new GiteeOauth();
            json = new ResultJson(SucessEnum.GITEE_LOGIN);
        }else if(type.equalsIgnoreCase("github")){
            oauth=new GithubOauth();
            json=new ResultJson(SucessEnum.GITHUB_LOGIN);
        }
        oauth.setCode(code);
        String reposeToken = oauth.sendTokenUrl();
        //TODO:进一步封装
        oauth.setToken(oauth.getToken(reposeToken));
        String userInfo = oauth.sendUserUrl();
        User user=oauth.changToUser(userInfo);
        userService.updataOrCreateUser(user);

        Cookie cookie = new Cookie("token", oauth.getToken());
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(cookie);
        //TODO:让用户去修改自己的信息。
        json.setData(user);
        return json;
    }
}
