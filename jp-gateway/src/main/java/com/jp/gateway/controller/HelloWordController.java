package com.jp.gateway.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : xh
 * @date : Created in 2022/7/20 9:38
 */
@RestController
@RequestMapping("test")
public class HelloWordController {

    @GetMapping("hello")
    public String hello(){
        return "hello word";
    }
}
