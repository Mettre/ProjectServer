package com.example.myproject.mapper;

import com.example.myproject.po.Users;

public interface LoginMapper {

    Users login(String phone, String newPassword);

}
