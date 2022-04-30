package com.community.utils;

import com.community.model.User;
import com.community.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class RequestUntils {
    public static User getToken(HttpServletRequest request, UserService userService){
        Cookie[] cookies = request.getCookies();
        AtomicReference<User> user;
        if(cookies==null) return null;
        List<User> token = Arrays.stream(cookies).filter(cookie -> {
            return cookie != null && cookie.getName().equals("token");
        }).map(e -> {
            if (e != null)
                return userService.getUser(e.getValue());
            return null;
        }).filter(e -> e != null).collect(Collectors.toList());
        if(token.size()==1)return  token.get(0);
        else  return null;
    }
}
