package com.jp.video.controller;

import com.jp.common.base.CommonResponse;
import com.jp.video.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : xh
 * @date : Created in 2022/7/1 18:42
 */
@RestController
@RequestMapping("/video")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 用户下单接口
     * @param userId
     * @param commodityCode
     * @return
     */
    @PostMapping("createOrder")
    public CommonResponse<String> createOrder(String userId, String commodityCode){
        return CommonResponse.success(orderService.createOrder(userId,commodityCode));
    }

    @GetMapping("getOrderNo")
    public CommonResponse<String> getOrderNo(String userId, String tenantId, HttpServletRequest request){
        System.out.println("Authorization:" + request.getHeader("Authorization"));
        return CommonResponse.success(orderService.getOrderNo(userId,tenantId));
    }

    /**
     * 测试负载均衡
     * @return
     */
    @GetMapping("lb")
    public String lb(){
        System.out.println("test lb");
        return "lb";
    }
}
