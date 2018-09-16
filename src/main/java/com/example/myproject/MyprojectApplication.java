package com.example.myproject;

import com.example.myproject.jwt.JwtFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan
@Configuration
@EnableAutoConfiguration(exclude = {JpaRepositoriesAutoConfiguration.class //禁止springboot自动加载持久化bean
})
public class MyprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyprojectApplication.class, args);
    }

    //过滤器
    @Bean
    public FilterRegistrationBean jwtFilter() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new JwtFilter());
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/api/user/loginEd/*");
        urlPatterns.add("/api/delivery/loginEd/*");
        urlPatterns.add("/api/shop/loginEd/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
