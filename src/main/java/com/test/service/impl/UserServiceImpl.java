package com.test.service.impl;

import com.test.common.Result;
import com.test.mapper.UserMapper;
import com.test.pojo.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        String userName = user.getUserName();
        String userPwd = user.getUserPwd();

        User user1=userMapper.getByUsername(userName);

        if (user1.getUserPwd() == userPwd){
            return user1;
        }
        return null;
    }
}
