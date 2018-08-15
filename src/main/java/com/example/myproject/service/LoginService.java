package com.example.myproject.service;

import com.example.myproject.po.Users;

public interface LoginService {

    Users login(String phone, String newPassword);
}
