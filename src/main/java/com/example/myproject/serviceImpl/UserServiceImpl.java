package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.UserMapper;
import com.example.myproject.pojo.Users;
import com.example.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Users login(String phone, String password) {
        return userMapper.login(phone, password);
    }

    @Override
    public int insert(Users record) {
        return userMapper.insert(record);
    }

    @Override
    public Users findUser(String phone) {
        return userMapper.selectUser(phone);
    }
}
