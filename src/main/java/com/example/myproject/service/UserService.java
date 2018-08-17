package com.example.myproject.service;

import com.example.myproject.pojo.Users;

public interface UserService {

    Users login(String phone, String password);

    int insert(Users record);

    Users findUser(String phone);
}
