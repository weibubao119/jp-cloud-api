package com.jp.module.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jp.module.user.dto.UserLoginDTO;
import com.jp.module.user.dto.UserRegisterDTO;
import com.jp.module.user.entity.Users;
import com.jp.module.user.vo.UserInfoVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : xh
 * @date : Created in 2022/7/4 10:48
 */
public interface UserService extends IService<Users> {

    String getOrderNo(String userId, String tenantId , HttpServletRequest request);

    /**
     * 登录
     */
    UserInfoVO login(UserLoginDTO userLoginDTO);

    /**
     * 注册
     */
    UserInfoVO register(UserRegisterDTO userRegisterDTO);
}
