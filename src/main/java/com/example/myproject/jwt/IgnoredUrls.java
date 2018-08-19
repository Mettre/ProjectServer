package com.example.myproject.jwt;


import com.google.common.base.Predicate;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 屏蔽的认证接口
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ignored")
public class IgnoredUrls {

    private String[] urls;

    private Predicate<String> urlList;
}
