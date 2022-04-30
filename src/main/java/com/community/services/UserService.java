package com.community.services;

import com.community.mapper.UserMapper;
import com.community.model.User;
import com.community.model.UserExample;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    @Setter
    private UserMapper userMapper;
    public User login(User user){
        UserExample userExample=new UserExample();
        userExample.createCriteria()
                .andPassWordEqualTo(user.getPassWord())
                .andAccountIdEqualTo(user.getAccountId());

        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()==1){
            return users.get(0);
        }
        return null;

    }
    public User getUser(String token){
        UserExample userExample=new UserExample();
        userExample.createCriteria().andTokenEqualTo(token);
        List<User> users = userMapper.selectByExample(userExample);
        if(users.size()==1)return users.get(0);
        return null;

    }
    public int updataUser(User user) {
        UserExample userExample = new UserExample();
        userExample.createCriteria()
                .andAccountIdEqualTo(user.getAccountId())
                .andPassWordEqualTo(user.getPassWord());
        List<User> users = userMapper.selectByExample(userExample);
        if (users.size() == 0) {
            return 0;
        } else {
            return userMapper.updateByPrimaryKey(user);
        }
    }


    public int createUser(User user) {
       int i=userMapper.insertSelective(user);
       return i;
    }
}
