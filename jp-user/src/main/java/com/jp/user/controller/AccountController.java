package com.jp.user.controller;

import com.jp.common.annotation.RedisSelect;
import com.jp.common.base.CommonResponse;
import com.jp.common.redis.RedisSelectSupport;
import com.jp.common.utils.DbRedisUtils;
import com.jp.module.user.dto.UserLoginDTO;
import com.jp.module.user.dto.UserRegisterDTO;
import com.jp.module.user.service.UserService;
import com.jp.module.user.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
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

    @Autowired
    private DbRedisUtils redisUtils;

    @Autowired
    private StringRedisTemplate redisTemplate;

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
    @RedisSelect(15)
    public CommonResponse<UserInfoVO> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO){
        return CommonResponse.success(userService.register(userRegisterDTO));
    }

    @GetMapping("test")
    @RedisSelect(2)
    public CommonResponse<Boolean> test(){
        redisUtils.set("2",11);
        String two = redisTemplate.opsForValue().get("two");
        System.out.println(two);
        RedisSelectSupport.select(3);//此处切换到db3库
        String three = redisTemplate.opsForValue().get("three");
        System.out.println(three);
        redisUtils.set("3",11);
        return CommonResponse.success(true);
    }
}
