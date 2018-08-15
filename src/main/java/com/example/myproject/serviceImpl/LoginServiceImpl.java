package com.example.myproject.serviceImpl;

import com.example.myproject.mapper.LoginMapper;
import com.example.myproject.pojo.Users;
import com.example.myproject.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

    private LoginMapper loginMapper;

    @Autowired
    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @Override
    public Users login(String phone, String newPassword) {
        return loginMapper.login(phone, newPassword);
    }
}
