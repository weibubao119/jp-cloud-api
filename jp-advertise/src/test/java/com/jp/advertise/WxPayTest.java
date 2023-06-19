package com.jp.advertise;

import com.github.binarywang.wxpay.bean.request.BaseWxPayRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.jp.advertise.service.WxPayService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : xh
 * @date : Created in 2022/7/26 15:35
 */
@SpringBootTest
public class WxPayTest {

    @Autowired
    private WxPayService wxPayService;

    /**
     * wx统一下单接口
     */
    @Test
    public void unifiedOrder(){
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        orderRequest.setBody("迷迭香充值中心");
        orderRequest.setOutTradeNo("O123456789012344566");
        orderRequest.setTotalFee(BaseWxPayRequest.yuanToFen("0.01"));//元转成分
        orderRequest.setOpenid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
        orderRequest.setSpbillCreateIp("127.0.0.1");
//        orderRequest.setTimeStart("20220726154010");
//        orderRequest.setTimeExpire("20220726154010");
        orderRequest.setProductId("123");
        WxPayUnifiedOrderResult result = wxPayService.unifiedOrder(orderRequest);
        System.out.println(result);
    }
}
