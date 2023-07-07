package com.jp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author : xh
 * @date : Created in 2022/7/1 11:25
 */
@SpringBootApplication
@EnableFeignClients
@EnableOpenApi
public class JpConfigureApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpConfigureApplication.class, args);
    }
}
