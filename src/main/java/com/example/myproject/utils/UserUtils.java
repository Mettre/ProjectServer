package com.example.myproject.utils;

import cn.hutool.core.util.StrUtil;
import com.example.myproject.pojo.Goods;
import com.example.myproject.pojo.Users;
import com.example.myproject.service.CartService;
import com.example.myproject.service.GoodsService;
import com.example.myproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserUtils {

    @Autowired
    public UserService loginService;

    @Autowired
    public GoodsService goodsService;

    @Autowired
    private CartService cartService;

    public static final UserUtils getInstance() {
        return MyUserUtilsHolder.INSTANCE;
    }

    public static class MyUserUtilsHolder {
        private static final UserUtils INSTANCE = new UserUtils();
    }


    public boolean hasUser(String userId) {
        if (StrUtil.isBlank(userId)) return false;
        Users user = loginService.findUserByUserId(userId);
        return user != null;
    }


    public boolean hasGoodS(Long goodId) {
        if (goodId <= 0) return false;
        Goods goods = goodsService.findGoodDetails(goodId);
        return goods != null;
    }

}
