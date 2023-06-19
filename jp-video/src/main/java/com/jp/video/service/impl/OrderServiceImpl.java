package com.jp.video.service.impl;

import com.jp.video.entity.OrderTbl;
import com.jp.common.exception.BizException;
import com.jp.common.utils.StringUtils;
import com.jp.video.feign.StorageFeign;
import com.jp.video.repository.OrderRepository;
import com.jp.video.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : xh
 * @date : Created in 2022/7/1 18:42
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private StorageFeign storageFeign;

    /**
     * 下单接口
     * @param userId 用户id
     * @param commodityCode 商品代码
     * @return
     */
    @Override
    @GlobalTransactional
    public String createOrder(String userId, String commodityCode) {
        try {
            System.out.println("事务id---------------------->" + RootContext.getXID());
            // 创建订单
            OrderTbl orderTbl = new OrderTbl();
            orderTbl.setUserId(userId);
            orderTbl.setCommodityCode(commodityCode);
            orderTbl.setCount(1); // 假设为1件
            orderTbl.setMoney(10); // 假设为十元

            // 保存订单
            orderRepository.save(orderTbl);

            // 保存订单成功后扣减库存
            storageFeign.deduct(commodityCode,orderTbl.getCount());

            return "success";
        }catch (Exception e){
            throw new BizException("创建订单失败");
        }

    }

    @Override
    public String getOrderNo(String userId, String tenantId) {
        System.out.println(tenantId);
        if (StringUtils.isNotEmpty(userId) && userId.equals("mdx123456")) {
            return "O111222333444";
        } else {
            throw new RuntimeException("单号不存在");
        }
    }
}
