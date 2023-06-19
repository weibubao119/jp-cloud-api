package com.jp.advertise.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author : xh
 * @date : Created in 2022/7/26 15:01
 */
@ConfigurationProperties(prefix = "wx.pay")
@Component
@Data
public class WxPayProperties {

    /**
     * 设置微信公众号或者小程序等的appId
     */
    private String appId;

    /**
     * 微信支付商户号
     */
    private String mchId;

    /**
     * 微信支付商户密钥
     */
    private String mchKey;

    /**
     * 支付回调地址
     */
    private String notifyUrl;
}
