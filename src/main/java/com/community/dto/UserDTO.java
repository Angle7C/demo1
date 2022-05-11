package com.community.dto;

import com.community.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;

@Data
@ToString
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String passWord;
    private Long accountId;
    private String avatarUrl;

    public UserDTO(User user){
        if(user!=null) {
            //TODO:密码加密
            this.setPassWord(user.getPassWord());
            this.setName(user.getName());
            this.setAvatarUrl(user.getAvatarUrl());
            this.setAccountId(user.getAccountId());
        }
    }
    public User toModel(){
        User user=new User();
        user.setAccountId(this.getAccountId());
        user.setPassWord(this.getPassWord());
        user.setAvatarUrl(this.getAvatarUrl());
        user.setName(this.getName());
        return user;
    }
}
