package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.UserMapper;
import com.example.myproject.pojo.Users;
import com.example.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("UserService")
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
    public Users findUserByPhone(String phone) {
        return userMapper.findUserByPhone(phone);
    }

    @Override
    public Users findUserByUserId(String userId) {
        return userMapper.findUserByUserId(userId);
    }

    @Override
    public int editUserInfo(Users users) {
        return userMapper.editUserInfo(users);
    }

    @Override
    public int modifyPassword(String oldPassword, String newPassword) {
        return userMapper.modifyPassword(oldPassword, newPassword);
    }

    @Override
    public int forgetPassword(String phone, String password) {
        return userMapper.forgetPassword(phone, password);
    }
}
