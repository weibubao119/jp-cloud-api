package com.jp.video.feign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author : xh
 * @date : Created in 2022/7/4 10:26
 */
@FeignClient(value = "mdx-shop-storage")
@Component
public interface StorageFeign {

    /**
     * 扣减库存
     * @param commodityCode
     * @param count
     * @return
     */
    @GetMapping("storage/deduct")
    String deduct(@RequestParam String commodityCode,@RequestParam Integer count);

}
