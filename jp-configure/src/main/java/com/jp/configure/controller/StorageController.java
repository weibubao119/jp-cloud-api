package com.jp.configure.controller;

import com.jp.configure.service.StorageService;
import com.jp.common.base.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : xh
 * @date : Created in 2022/7/1 18:42
 */
@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService service;

    @GetMapping("/deduct")
    public CommonResponse deduct(String commodityCode, int count){
        service.deduct(commodityCode, count);
        return CommonResponse.success();
    }
}
