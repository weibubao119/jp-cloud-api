package com.jp.module.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jp.module.user.dto.MdxUserDTO;
import com.jp.module.user.entity.UsersEntity;
import com.jp.module.user.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : xh
 * @date : Created in 2022/7/4 10:48
 */
public interface UserService extends IService<UsersEntity> {

    String getOrderNo(String userId, String tenantId , HttpServletRequest request);

    /**
     * 登录
     */
    LoginVo login(MdxUserDTO mdxUserDTO);
}
