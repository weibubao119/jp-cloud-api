package com.jp.user.service;

import com.jp.user.dto.MdxUserDTO;
import com.jp.user.vo.LoginVo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : xh
 * @date : Created in 2022/7/4 10:48
 */
public interface UserService {

    String getOrderNo(String userId, String tenantId , HttpServletRequest request);

    /**
     * 登录
     * @param mdxUserDTO
     * @return
     */
    LoginVo login(MdxUserDTO mdxUserDTO);
}
