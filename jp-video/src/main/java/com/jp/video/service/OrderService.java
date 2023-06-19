package com.jp.video.service;

/**
 * @author : xh
 * @date : Created in 2022/7/1 18:40
 */
public interface OrderService {

    /**
     * 下单接口
     * @param userId 用户id
     * @param commodityCode 商品代码
     * @return
     */
    String createOrder(String userId, String commodityCode);

    /**
     * 获取订单号
     *
     * @param userId
     * @return
     */
    String getOrderNo(String userId, String tenantId);
}
