package com.example.myproject.jwt;

import lombok.Data;

@Data
public class AccessToken {

    private String access_token;

    private String token_type;

    private long expires_in;

    public AccessToken() {
    }

    public AccessToken(String access_token, String token_type, long expires_in) {
        this.access_token = access_token;
        this.token_type = token_type;
        this.expires_in = expires_in;
    }
}
