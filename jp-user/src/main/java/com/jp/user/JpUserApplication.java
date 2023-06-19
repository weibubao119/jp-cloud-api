package com.jp.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : xh
 * @date : Created in 2022/7/1 11:25
 */
@SpringBootApplication
@EnableFeignClients
@ComponentScan(basePackages = {"com.jp.*"})
public class JpUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpUserApplication.class, args);
    }
}
