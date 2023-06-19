package com.jp.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Charsets;
import com.jp.user.entity.WxUserInfo;
import com.jp.user.config.WeChatProperties;
import com.jp.user.service.WeChatLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

/**
 * @author : xh
 * @date : Created in 2022/7/22 15:53
 */
@Service
@RefreshScope
@Slf4j
public class WeChatLoginServiceImpl implements WeChatLoginService {

    @Autowired
    private WeChatProperties weChatProperties;

    @Autowired
    private RestTemplate restTemplate;


    /**
     * 获取授权链接
     * @return
     */
    @Override
    public String getAuthorizationUrl() {
        String authorizationUrl = "https://open.weixin.qq.com/connect/oauth2/authorize?" +
                "appid=%s" +
                "&redirect_uri=%s" +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "#wechat_redirect";

        // 使用urlEncoding对uri进行编码，防止其他浏览器解析错误
        String redirectUrl = "";
        try {
            redirectUrl = URLEncoder.encode(weChatProperties.getCallBackUrl(), Charsets.UTF_8.name());
        }catch (Exception e){
            e.printStackTrace();
        }
        String resultUrl = String.format(authorizationUrl, weChatProperties.getAppId(), redirectUrl);
        return resultUrl;
    }


    /**
     * 获取扫码登录二维码
     * @param request
     * @param response
     */
    @Override
    public void getQrCode(HttpServletRequest request, HttpServletResponse response) {
        // 微信扫码登录链接为微信开放平台
        String oauthUrl = "https://open.weixin.qq.com/connect/qrconnect?appid=appId&redirect_uri=REDIRECT_URI&response_type=code&scope=snsapi_login&state=STATE#wechat_redirect";
        oauthUrl =  oauthUrl.replace("appId","").replace("REDIRECT_URI","");

        // 使用urlEncoding对uri进行编码，防止其他浏览器解析错误
        String redirectUrl = "";
        try {
            redirectUrl = URLEncoder.encode(weChatProperties.getCallBackUrl(), Charsets.UTF_8.name());
        }catch (Exception e){
            e.printStackTrace();
        }
        String resultUrl = String.format(oauthUrl, weChatProperties.getAppId(), redirectUrl);
        try {
            response.sendRedirect(resultUrl);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取accessToken 并获取用户信息
     * @param code
     * @param request
     * @param response
     */
    @Override
    public void callBack(String code, HttpServletRequest request, HttpServletResponse response) {
        // 从redis中拿token查看是否过期

        // 如果过期 请求wx获取access_token
        String accessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";
        MultiValueMap<String, String> accessTokenParam = new LinkedMultiValueMap<>();
        accessTokenParam.add("appid", weChatProperties.getAppId());
        accessTokenParam.add("secret", weChatProperties.getAppSecret());
        accessTokenParam.add("code", code);
        accessTokenParam.add("grant_type", "authorization_code");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(accessTokenUrl);
        URI uri = builder.queryParams(accessTokenParam).build().encode().toUri();

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        if (result.getStatusCodeValue() == 200){
            Map body = JSONObject.parseObject(result.getBody(), Map.class);
            if (body.get("errcode") != null) {
                Integer errCode = (Integer) body.get("errcode");
                String errMsg = (String) body.get("errmsg");
                log.info("获取access_token异常,errMsg:" + errMsg + ", errCode:" + errCode);
            }else {
                String accessToken = (String) body.get("access_token");
                String openid = (String) body.get("openid");
                Integer expiresIn = (Integer) body.get("expires_in");

                log.info("access_token:" + accessToken + ",openid:" + openid);
                // 将 accessToken 存入redis

                // 通过accessToken和openid获取用户信息
                WxUserInfo wxUserInfo = this.getWxUserInfo(accessToken, openid);
                System.out.println(wxUserInfo);
            }

            // 此处假装跳转到登录页面
            try {
                response.sendRedirect("https://blog.csdn.net/qq_38374397/article/details/125874882");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取微信用户信息
     * @param accessToken
     * @param openid
     * @return
     */
    public WxUserInfo getWxUserInfo(String accessToken,String openid){
        WxUserInfo wxUserInfo = null;
        String userInfoUrl = "https://api.weixin.qq.com/sns/userinfo";
        MultiValueMap<String, String> userInfoParam = new LinkedMultiValueMap<>();
        userInfoParam.add("access_token", accessToken);
        userInfoParam.add("openid", openid);
        userInfoParam.add("lang", "zh_CN");

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(userInfoUrl);
        URI uri = builder.queryParams(userInfoParam).build().encode().toUri();

        ResponseEntity<String> result = restTemplate.getForEntity(uri, String.class);
        if (result.getStatusCodeValue() == 200){
            Map body = JSONObject.parseObject(result.getBody(), Map.class);
            if (body.get("errcode") != null) {
                Integer errCode = (Integer) body.get("errcode");
                String errMsg = (String) body.get("errmsg");
                log.info("获取微信用户信息异常,errMsg:" + errMsg + ", errCode:" + errCode);
            }else {
                wxUserInfo = JSONObject.parseObject(result.getBody(), WxUserInfo.class);
            }

        }
        return wxUserInfo;
    }
}
