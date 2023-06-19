package com.jp.configure.service.impl;
import com.jp.common.exception.BizException;
import com.jp.configure.entity.StorageTbl;
import com.jp.configure.repository.StorageRepository;
import com.jp.configure.service.StorageService;
import io.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : xh
 * @date : Created in 2022/7/1 18:42
 */
@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository storageRepository;

    /**
     * 扣减库存
     * @param commodityCode
     * @param count
     */
    @Override
    public void deduct(String commodityCode, int count) {
        System.out.println("事务id---------------------->" + RootContext.getXID());
        StorageTbl storageTbl = storageRepository.findByCommodityCode(commodityCode);
        if (storageTbl == null){
            throw new BizException("storageTbl is null");
        }

        // 模拟异常
        System.out.println(1 / 0);

        // 这里先不考虑超卖的情况
        storageTbl.setCount(storageTbl.getCount() - count);
        // 使用jpa 存在就更新
        storageRepository.save(storageTbl);
    }
}
