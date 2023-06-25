package com.jp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.oas.annotations.EnableOpenApi;

/**
 * @author : xh
 * &#064;date  : Created in 2022/7/1 11:25
 */
@SpringBootApplication
@EnableFeignClients
@EnableOpenApi
public class JpUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpUserApplication.class, args);
    }
}
