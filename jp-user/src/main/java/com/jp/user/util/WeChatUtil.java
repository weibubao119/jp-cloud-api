package com.jp.user.util;

import com.alibaba.fastjson.JSONObject;
import com.jp.user.config.WeChatProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author : xh
 * @date : Created in 2022/7/25 14:46
 */
@Component
public class WeChatUtil {

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * 获取指定长度数字随机字符串
     *
     * @param length
     * @return
     */
    public static String getRandomNumber(int length) {
        int rs = (int) ((Math.random() * 9 + 1) * Math.pow(10, length - 1));
        return String.valueOf(rs);
    }

    /**
     *  获取accessToken
     * @return
     */
    public String getAccessToken(){
        String accessToken = null;
        //根据appid和appsecret获取access_token
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
        String getTokenUrl = url.replace("APPID", weChatProperties.getAppId()).replace("APPSECRET", weChatProperties.getAppSecret());
        ResponseEntity<String> result = restTemplate.getForEntity(getTokenUrl, String.class);
        if (result.getStatusCodeValue() == 200){
            JSONObject jsonObject = JSONObject.parseObject(result.getBody());
            accessToken = jsonObject.getString("access_token");
        }
        return accessToken ;
    }

}
