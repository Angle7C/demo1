package com.community.utils;

import com.community.model.User;
import com.community.services.UserService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class RequestUntils {
    public static User getToken(HttpServletRequest request, UserService userService){
        Cookie[] cookies = request.getCookies();
        if(cookies==null) return null;
        List<User> token=new ArrayList<>();
        for(Cookie item:cookies){
            if(item.getName().equals("token")){
               User user=userService.getUser(item.getValue());
               if (user!=null){
                   token.add(user);
               }
            }
            if(token.size()>1) break;
        }
        if(token.size()==1)
            return  token.get(0);
        else  return null;
    }
}
