package com.jp.user.controller;

import com.jp.common.base.CommonResponse;
import com.jp.user.service.WeChatLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : xh
 * @date : Created in 2022/7/22 15:48
 */
@RestController
@RequestMapping("/user/wechat")
public class WeChatLogin {

    @Autowired
    private WeChatLoginService weChatLoginService;

    /**
     * 获取微信授权连接 -- 微信公众平台
     * @return
     */
    @GetMapping("getAuthorizationUrl")
    public CommonResponse<String> getAuthorizationUrl(){
        return CommonResponse.success(weChatLoginService.getAuthorizationUrl());
    }

    /**
     * 获取微信登录二维码 -- 微信开放平台
     * @param request
     * @param response
     * @return
     */
    @GetMapping("getQrCode")
    public void getQrCode(HttpServletRequest request,HttpServletResponse response){
        weChatLoginService.getQrCode(request,response);
    }

    /**
     * 微信回调/并通过code获取 access_token
     * @param code
     * @param request
     * @param response
     */
    @GetMapping("callBack")
    public void callBack(@RequestParam("code") String code,
                         HttpServletRequest request,
                         HttpServletResponse response){
        System.out.println(code);
        weChatLoginService.callBack(code,request,response);
    }
}
