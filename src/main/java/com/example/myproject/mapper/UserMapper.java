package com.example.myproject.mapper;

import com.example.myproject.pojo.Users;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    Users login(@Param(value = "phone") String phone, @Param(value = "password") String password);

    int insert(Users users);

    Users selectUser(String phone);

}
