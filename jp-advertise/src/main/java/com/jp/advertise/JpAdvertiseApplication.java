package com.jp.advertise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : xh
 * @date : Created in 2022/7/1 11:25
 */
@SpringBootApplication
@EnableFeignClients
public class JpAdvertiseApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpAdvertiseApplication.class, args);
    }
}
