package com.jp.common.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Component;

/**
 * @author : xh
 * @date : Created in 2022/7/6 10:24
 */
@Component
public class UserSentinelResourceHandler {

    /**
     * sentinelA资源熔断兜底方法
     * @param throwable
     * @return
     */
    public static String sentinelAResource(Throwable throwable){
        System.out.println("触发熔断，服务不可用");
        return "触发熔断，服务不可用";
    }

    public static String hotspotResource(String userId, String shopId,BlockException blockException){
        System.out.println("您被认为恶意访问，触发热点限流");
        return "您被认为恶意访问，触发热点限流";
    }
}
