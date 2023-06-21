package com.jp.module.user.service.impl;

import com.jp.module.user.service.UserService;
import com.jp.user.dto.MdxUserDTO;
import com.jp.common.exception.BizException;
import com.jp.common.manager.RedisManager;
import com.jp.common.utils.JWTProvider;
import com.jp.common.utils.StringUtils;
import com.jp.user.constant.UserConstant;
import com.jp.user.entity.MdxUser;
import com.jp.user.feign.OrderFeign;
import com.jp.user.repository.MdxUserRepository;
import com.jp.user.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author : xh
 * @date : Created in 2022/7/4 10:48
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OrderFeign orderFeign;

    @Autowired
    private MdxUserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private RedisManager redisManager;

    @Value("${jwt.prefix}")
    private String prefix;

    /**
     * 登录
     * @param mdxUserDTO
     * @return
     */
    @Override
    public LoginVo login(MdxUserDTO mdxUserDTO) {
        MdxUser mdxUser = userRepository.findByUserName(mdxUserDTO.getUserName());
        if (mdxUser == null){
            throw new BizException("用户不存在");
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 判断用户名密码是否正确
        if (StringUtils.isEmpty(mdxUser.getUserName()) ||
                ! encoder.matches(mdxUserDTO.getPassword(), mdxUser.getPassword())){
            throw new BizException("用户名或者密码错误");
        }
        // 生成token
        String token = jwtProvider.generateToken(mdxUser.getUserName());

        // 将token存入redis
        redisManager.set(UserConstant.USER_TOKEN_KEY_REDIS + mdxUser.getUserName(),token,604800);

        return LoginVo.builder()
                .userId(mdxUser.getUserId().toString())
                .userName(mdxUser.getUserName())
                .token(prefix + " " + token).build();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("admin");
        System.out.println(password);
    }

    @Override
    public String getOrderNo(String userId, String tenantId, HttpServletRequest request) {
        return orderFeign.getOrderNo(userId,tenantId, request.getHeader("token"));
    }
}
