package com.jp.common.feign.order;
import com.jp.common.handler.OrderFeignHandler;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : xh
 * @date : Created in 2022/7/4 10:26
 */
@FeignClient(value = "video-service",fallback = OrderFeignHandler.class)
@Component
public interface OrderFeign {

    @GetMapping("order/getOrderNo")
    String getOrderNo(@RequestParam String userId,@RequestParam String tenantId,@RequestHeader("Authorization") String token);

}
