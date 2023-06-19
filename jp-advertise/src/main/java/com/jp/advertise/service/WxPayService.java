package com.jp.advertise.service;

import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundRequest;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.WxPayRefundResult;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author : xh
 * @date : Created in 2022/7/26 14:58
 */
public interface WxPayService {

    /**
     * 统一下单  详见https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_1
     * 在发起微信支付前，需要调用统一下单接口，获取"预支付交易会话标识"  -- h5支付除外
     * 接口地址：https://api.mch.weixin.qq.com/pay/unifiedorder
     *
     * @param request 请求对象，注意一些参数如appid、mchid等不用设置，方法内会自动从配置对象中获取到（前提是对应配置中已经设置）
     */
    WxPayUnifiedOrderResult unifiedOrder(WxPayUnifiedOrderRequest request);

    /**
     * 申请退款
     * 详细入参 详见 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
     * @param request
     * @return
     */
    WxPayRefundResult refund(WxPayRefundRequest request);

    /**
     * 统一下单支付回调
     * @param xmlData
     * @return
     */
    String parseOrderNotifyResult(String xmlData);

    /**
     * 退款回调
     * @param xmlData
     * @return
     */
    String parseRefundNotifyResult(String xmlData);
}
