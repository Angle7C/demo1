package com.community.controller;
import com.community.Inter.Oauth;
import com.community.dto.UserDTO;
import com.community.enums.SucessEnum;
import com.community.enums.ErrorEnum;
import com.community.model.ResultJson;
import com.community.model.User;
import com.community.oauthuser.GiteeOauth;
import com.community.oauthuser.GithubOauth;
import com.community.services.UserService;
import com.community.utils.UserUntils;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
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
    //light:OK

    @PostMapping("/logIn")
    public ResultJson logIn(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestBody UserDTO userDTO) {
        User user=new User();
        BeanUtils.copyProperties(userDTO,user);
        System.out.println(user);
        ResultJson<UserDTO> json = new ResultJson<UserDTO>(10001, "第一次登陆");
        Cookie[] cookies = request.getCookies();
        User login = null;
        Cookie cookie = new Cookie("token", null);
        if (cookies == null ) {
            login = userService.login(user);
            cookie.setValue(UUID.randomUUID().toString());
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
                login = userService.login(user);
                cookie.setValue(UUID.randomUUID().toString());
                json.setMessage("第一次登录");

            } else {
                login = token.get(0);
                cookie.setValue(login.getToken());
                json.setMessage("持久登录");
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
            UserUntils.addUser(login);
            userDTO = new UserDTO(login);
            json.setData(userDTO);
        }
        return json;
    }
    //light:OK

    @GetMapping("/logOut")
    public ResultJson logOut(
            HttpServletRequest request,
            HttpServletResponse response) {
        User user = UserUntils.checkUser(request);
        if(user==null)  return new ResultJson<Object>(ErrorEnum.CHECK_USER_LOGIN);
        UserUntils.remove(user);
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ResultJson<Object>(SucessEnum.USER_LOHOUT);
    }
    //light:OK
    @GetMapping("/checkLogin")
    public static ResultJson<UserDTO> checkLog(
            HttpServletRequest request) {
        ResultJson<UserDTO> json = new ResultJson<>(ErrorEnum.CHECK_USER_LOGIN);
        User token = UserUntils.checkUser(request);
        if(token!=null){
            json.setData(new UserDTO(token));
            json.ok(SucessEnum.USER_LOGIN);
        }
        return json;
    }
    //light:ok
    @PutMapping("logOn")
    public ResultJson<UserDTO> logOn(@RequestBody UserDTO userDTO ,HttpServletResponse response) {
        ResultJson<UserDTO> json=new ResultJson<>(10001,"已存在用户");
        User user= userDTO.toModel();
        if (user.getAccountId() == null){
            json.setMessage("注册成功");
            Cookie cookie=new Cookie("token",UUID.randomUUID().toString());
            user.setToken(cookie.getValue());
            user.setType("Self");
            //TODO:加密
//            user.setPassWord(MD5Encoder.encode();
            userService.updataOrCreateUser(user);
            BeanUtils.copyProperties(user,userDTO);
            json.setData(userDTO);
        }
        return json;
    }
    //light:OK

    @GetMapping("/log/{type}")
    public ResultJson log(HttpServletRequest request, HttpServletResponse response, @PathVariable("type") String type) throws IOException {
        String url = null;
        if (type.toLowerCase().equals("gitee")) {
            url = "https://gitee.com/oauth/authorize?client_id=533270564bc0c9d9e10c23c6f802f05e2534513612651c01d5692139536492e7&redirect_uri=http://localhost:8081/backCall/" + type + "&response_type=code";
        } else if (type.toLowerCase().equals("github")) {
            url = "https://github.com/login/oauth/authorize?client_id=87d98f857294a395b413&redirect_uri=http://localhost:8081/backCall/Github";
        } else {
            url = null;
        }
        return new ResultJson<String>("重定向",url);
    }
    //light:OK

    @GetMapping("/backCall/{type}")
    public ResultJson<UserDTO> logUser(HttpServletRequest request, HttpServletResponse response, @RequestParam("code") String code, @PathVariable("type") String type) throws IOException {
        ResultJson json = null;
        Oauth oauth = null;
        UserDTO userDTO=new UserDTO();
        if (type.equalsIgnoreCase("gitee")) {
            oauth = new GiteeOauth();
            json = new ResultJson(SucessEnum.GITEE_LOGIN);
        } else if (type.equalsIgnoreCase("github")) {
            oauth = new GithubOauth();
            json = new ResultJson(SucessEnum.GITHUB_LOGIN);
        }
        oauth.setCode(code);
        String reposeToken = oauth.sendTokenUrl();
        oauth.setToken(oauth.getToken(reposeToken));
        String userInfo = oauth.sendUserUrl();
        User user = oauth.changToUser(userInfo);
        userService.updataOrCreateUser(user);
        BeanUtils.copyProperties(user,userDTO);
        Cookie cookie = new Cookie("token", oauth.getToken());
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(cookie);
        response.sendRedirect("http://localhost:8080");
        //TODO:让用户去修改自己的信息。
        json.setData(userDTO);
        return json;
    }

}
