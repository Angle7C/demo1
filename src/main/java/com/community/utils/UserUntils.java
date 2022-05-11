package com.community.utils;

import com.community.model.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class UserUntils {
    private static final Map<String, User> tokenMap = new HashMap<>();

    public static User checkUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        try {
            Optional<User> token = Arrays.stream(cookies).parallel()
                    .filter(item -> {
                        return item.getName().equals("token");
                    }).map(item -> {
                        return tokenMap.get(item.getValue());
                    }).filter(item -> {
                        return item != null;
                    }).limit(1).findFirst();
            return token.orElse(null);
        }catch (NullPointerException e){
            LogUtil.warn("检查不到登录用户");

        }
        return null;

    }
    public static void addUser(String token,User user){
        tokenMap.put(token,user);
    }
    public static void addUser(User user){
        tokenMap.put(user.getToken(), user);
    }
    public static void remove(String token){
        tokenMap.remove(token);
    }
    public static void remove(User user){
        tokenMap.remove(user.getToken());
    }
}
