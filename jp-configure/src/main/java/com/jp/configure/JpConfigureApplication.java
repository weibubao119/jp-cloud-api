package com.jp.configure;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author : xh
 * @date : Created in 2022/7/1 11:25
 */
@SpringBootApplication
@EnableFeignClients
@EnableAutoDataSourceProxy
public class JpConfigureApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpConfigureApplication.class, args);
    }
}
