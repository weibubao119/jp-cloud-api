package com.jp.configure.service;

/**
 * @author : xh
 * @date : Created in 2022/7/1 18:40
 */
public interface StorageService {

    /**
     * 扣除存储数量
     */
    void deduct(String commodityCode, int count);
}
