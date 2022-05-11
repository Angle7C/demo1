package com.community.services;

import com.community.mapper.UserMapper;
import com.community.model.User;
import com.community.model.UserExample;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
    public User getUser(Long id){
        return userMapper.selectByPrimaryKey(id);
    }
    @Transactional
    public int updataOrCreateUser(User user) {
        if (user.getAccountId() == null) {
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            return  userMapper.insert(user);
        } else {
            user.setGmtModified(System.currentTimeMillis());
            return userMapper.updateByPrimaryKey(user);
        }
    }
}
