package com.jp.video.controller;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author : xh
 * @date : Created in 2022/7/1 14:48
 */
@RestController
@RequestMapping("/video")
@RefreshScope
public class HelloWordController {

    @Value("${video.videoId}")
    private String videoId;

    /**
     * 测试获取nacos配置中心配置
     * @return
     */
    @GetMapping("getVideoConfigTest")
    public String getVideoConfigTest(){
        return videoId != null ? videoId : "未获取到配置";
    }
}
