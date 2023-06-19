package com.jp.advertise.service.impl;

import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.jp.advertise.config.WxPayProperties;
import com.jp.advertise.service.WxPayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : xh
 * @date : Created in 2022/7/26 14:59
 */
@Service
@Slf4j
public class WxPayServiceImpl implements WxPayService {

    @Autowired
    private WxPayProperties wxPayProperties;

    @Autowired
    private com.github.binarywang.wxpay.service.WxPayService wxService;

    /**
     * 微信统一下单接口
     * @param request
     * @return
     */
    @Override
    public WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request) {
        try {
            request.setNotifyUrl(wxPayProperties.getNotifyUrl());
            request.setTradeType("MWEB"); // MWEB为h5支付
            return wxService.createOrder(request);
        } catch (Exception e) {
            log.error("微信支付失败！订单号：{},原因:{}", request.getOutTradeNo(), e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 退款
     * @param request
     * @return
     */
    @Override
    public WxPayRefundResult refund(WxPayRefundRequest request) {
        try {
            return wxService.refund(request);
        } catch (Exception e) {
            log.error("微信退款失败！订单号：{},原因:{}", request.getOutTradeNo(), e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 统一下单支付回调
     * @param xmlData
     * @return
     */
    @Override
    public String parseOrderNotifyResult(String xmlData) {

        try {
            final WxPayOrderNotifyResult notifyResult = this.wxService.parseOrderNotifyResult(xmlData);
            // TODO 根据自己业务场景需要构造返回对象
            System.out.println(notifyResult);
        }catch (WxPayException e){
            e.printStackTrace();
        }
        return WxPayNotifyResponse.success("成功");
    }

    /**
     * 退款回调
     * @param xmlData
     * @return
     */
    @Override
    public String parseRefundNotifyResult(String xmlData) {
        try {
            final WxPayRefundNotifyResult result = this.wxService.parseRefundNotifyResult(xmlData);
            // TODO 根据自己业务场景需要构造返回对象
            System.out.println(result);
        }catch (WxPayException e){
            e.printStackTrace();
        }
        return WxPayNotifyResponse.success("成功");
    }
}
