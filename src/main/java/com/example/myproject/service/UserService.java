package com.example.myproject.service;

import com.example.myproject.pojo.Users;

public interface UserService {

    Users login(String phone, String password);

    int insert(Users record);

    Users findUserByPhone(String phone);

    Users findUserByUserId(String userId);

    int editUserInfo(Users users);

    int modifyPassword(String oldPassword, String newPassword);

    int forgetPassword(String phone, String password);
}
