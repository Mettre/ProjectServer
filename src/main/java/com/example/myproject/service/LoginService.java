package com.example.myproject.service;

import com.example.myproject.pojo.Users;

public interface LoginService {

    Users login(String phone, String password);

    int insert(Users record);

    Users selectUser(String phone);
}
