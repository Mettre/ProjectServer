package com.example.myproject.utils;

import io.jsonwebtoken.Claims;

import javax.servlet.http.HttpServletRequest;

public class TokenUtils {

    public static String getUserId(HttpServletRequest request) {
        final Claims claims = (Claims) request.getAttribute("claims");
        return claims.getSubject();
    }
}
