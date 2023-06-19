package com.jp.advertise.controller;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.jp.common.base.CommonResponse;
import com.jp.advertise.service.WxPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : xh
 * @date : Created in 2022/7/26 15:00
 */
@RestController
@RequestMapping("pay")
public class WxPayController {

    @Autowired
    private WxPayService wxPayService;


    /**
     * 统一下单
     * @param request
     * @return
     * @throws WxPayException
     */
    @PostMapping("/unifiedOrder")
    public CommonResponse<WxPayUnifiedOrderResult> unifiedOrder(@RequestBody WxPayUnifiedOrderRequest request) throws WxPayException {
        return CommonResponse.success(wxPayService.unifiedOrder(request));
    }

    /**
     * 申请退款
     * @param request
     * @return
     * @throws WxPayException
     */
    @PostMapping("/refund")
    public CommonResponse<WxPayRefundResult> refund(@RequestBody WxPayRefundRequest request) throws WxPayException {
        return CommonResponse.success(wxPayService.refund(request));
    }


    /**
     * 统一下单支付回调
     * @param xmlData
     * @return
     * @throws WxPayException
     */
    @PostMapping("/notify/order")
    public CommonResponse<String> parseOrderNotifyResult(@RequestBody String xmlData) throws WxPayException {
        return CommonResponse.success(wxPayService.parseOrderNotifyResult(xmlData));
    }

    /**
     * 退款回调
     * @param xmlData
     * @return
     * @throws WxPayException
     */
    @PostMapping("/notify/refund")
    public CommonResponse<String> parseRefundNotifyResult(@RequestBody String xmlData) throws WxPayException {
        return CommonResponse.success(wxPayService.parseRefundNotifyResult(xmlData));
    }
}
