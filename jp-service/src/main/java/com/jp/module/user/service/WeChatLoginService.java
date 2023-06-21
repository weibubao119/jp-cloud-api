package com.jp.module.user.service;

import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : xh
 * @date : Created in 2022/7/22 15:51
 */
public interface WeChatLoginService {

    /**
     * 获取授权链接
     * @return
     */
    String getAuthorizationUrl();


    void callBack(String code, HttpServletRequest request, HttpServletResponse response);

    void getQrCode(HttpServletRequest request, HttpServletResponse response);
}
