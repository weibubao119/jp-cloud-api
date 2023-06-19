package com.jp.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : xh
 * @date : Created in 2022/7/22 17:06
 */
@ConfigurationProperties(prefix = "wechat.public")
@Component
@Data
public class WeChatProperties {

    private String appId;

    private String appSecret;

    private String callBackUrl;

    private String orderTemplateId;
}
