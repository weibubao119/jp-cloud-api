package com.jp.user.controller;

import com.jp.common.base.CommonResponse;
import com.jp.module.user.dto.UserLoginDTO;
import com.jp.module.user.service.UserService;
import com.jp.module.user.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * @author : xh
 * @date : Created in 2022/7/4 10:50
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "用户账户")
public class AccountController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping("login")
    @ApiOperation(value = "登陆")
    public CommonResponse<UserInfoVO> login(@RequestBody @Valid UserLoginDTO userLoginDTO){
        return CommonResponse.success(userService.login(userLoginDTO));
    }


    /**
     * 注册
     * @param
     * @return
     */
    @PostMapping("register")
    @ApiOperation(value = "注册")
    public CommonResponse<Boolean> register(){
        return CommonResponse.success(true);
    }
}
