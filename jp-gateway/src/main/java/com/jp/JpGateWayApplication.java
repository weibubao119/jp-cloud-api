package com.jp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author : xh
 * @date : Created in 2022/7/1 11:25
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableFeignClients
//@ComponentScan(basePackages = {"com.jp.common","com.jp.gateway"})
public class JpGateWayApplication {
    public static void main(String[] args) {
        SpringApplication.run(JpGateWayApplication.class, args);
    }
}
