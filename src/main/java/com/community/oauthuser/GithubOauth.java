package com.community.oauthuser;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.community.Inter.Oauth;
import com.community.model.User;
import com.community.utils.JsonUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

public class GithubOauth implements Oauth {
    private static final String tokenUrl ="https://github.com/login/oauth/access_token?code=%s";
    private static final String userUrl="https://api.github.com/user";
    private static final String clientId="87d98f857294a395b413";
    private static final String redirectUrl="http://localhost:8081/backCall/Github";
    private static final String clientSecret="e0a8f18857de9bf1d139b271160f4b274604590b";
    private static final Map<String,Object> map=new HashMap<>();
    static {
        map.put("client_id",clientId);
//        map.put("redirect_uri",redirectUrl);
        map.put("client_secret",clientSecret);
    }
    @Getter
    private String code=null;
    @Setter
    @Getter
    private String token=null;
    public  void setCode(String code){
        this.code=code;
    }
    public String getToken(String str){
        return str.split("&")[0].replace("access_token=","");
    }
    @Override
    public String getTokenUrl() {
        if(code==null) throw new IllegalStateException("没有获得code");
        return String.format(tokenUrl,code);
    }

    @Override
    public String getAvatar(String str) {
        return JsonUtil.getAttribute(str,"avatar_url");
    }

    @Override
    public String getLogin(String str) {
        return JsonUtil.getAttribute(str,"login");
    }

    @Override
    public String getName(String str) {
        return JsonUtil.getAttribute(str,"name");
    }

    @Override
    public String getId(String str) {
        return JsonUtil.getAttribute(str,"avatar_url");
    }

    @Override
    public String getType() {
        return "Gitee";
    }

    @Override
    public String getUserUrl() {
        return  userUrl;
    }

    @Override
    public String sendTokenUrl() {
        return HttpUtil.post(getTokenUrl(),getBody());
    }

    @Override
    public String sendUserUrl() {
        String authorization = HttpRequest.get(userUrl)
                .header("Authorization", "token " + token)
                .execute().body();
        return authorization;
    }

    @Override
    public Map<String, Object> getBody() {
        return map;
    }

    @Override
    public User changToUser(String userInfo) {
        return null;
    }

}
