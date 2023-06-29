package com.jp.user.controller;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.jp.module.user.dto.MdxUserDTO;
import com.jp.common.base.CommonResponse;
import com.jp.module.user.service.UserService;
import com.jp.module.user.vo.LoginVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : xh
 * @date : Created in 2022/7/4 10:50
 */
@RestController
@RequestMapping("/user")
@Api(tags = {"用户"},value = "用户操作")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param mdxUserDTO
     * @return
     */
    @PostMapping("login")
    @ApiOperation(value = "登陆")
    public CommonResponse<LoginVo> login(@RequestBody MdxUserDTO mdxUserDTO){
        return CommonResponse.success(userService.login(mdxUserDTO));
    }

    /**
     * 测试openFeign获取订单服务接口
     * @param userId
     * @param tenantId
     * @param request
     * @return
     */
    @GetMapping("getOrderNo")
    @ApiOperation(value = "获取订单")
    @SentinelResource(value = "getOrderNoResource",blockHandler = "",blockHandlerClass = UserController.class)
    public CommonResponse<String> getOrderNo(String userId, String tenantId, HttpServletRequest request){
        return CommonResponse.success(userService.getOrderNo(userId,tenantId,request));
    }


    /**
     * 限流后续操作方法
     * @param e
     * @return
     */
    public static CommonResponse<String> getOrderNoBlockHandler(String userId, String tenantId, HttpServletRequest request,BlockException e){
        String msg = "不好意思，前方拥挤，请您稍后再试";
        return CommonResponse.success(msg);
    }

}
