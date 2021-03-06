package com.example.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
public class HelloController {

    /**
     * 本地访问内容地址 ：http://localhost:8080/lmycc/hello
     *
     * @param map
     * @return
     */
    @RequestMapping("/text")
    public String helloHtml(HashMap<String, Object> map) {
        map.put("hello", "欢迎进入HTML页面");
        return "/hello";
    }

    @RequestMapping("/link")
    public String linkHtml() {
        return "/link";
    }

    @RequestMapping("/interactive")
    public String interactiveHtml() {
        return "/android";
    }
}