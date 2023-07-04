package com.jp.module.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jp.common.constant.DomainConstant;
import com.jp.common.utils.MD5Util;
import com.jp.module.user.dao.UserMapper;
import com.jp.module.user.dto.UserLoginDTO;
import com.jp.module.user.entity.Users;
import com.jp.module.user.entity.UsersAvatar;
import com.jp.module.user.entity.UsersVip;
import com.jp.module.user.repository.UserAvatarRepository;
import com.jp.module.user.repository.UserVipRepository;
import com.jp.module.user.service.UserService;
import com.jp.common.exception.BizException;
import com.jp.common.manager.RedisManager;
import com.jp.common.utils.JWTProvider;
import com.jp.common.constant.UserConstant;
import com.jp.common.feign.order.OrderFeign;
import com.jp.module.user.vo.UserInfoVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : xh
 * @date : Created in 2022/7/4 10:48
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, Users> implements UserService{

    @Autowired
    private OrderFeign orderFeign;

    @Autowired
    private MD5Util md5Util;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserAvatarRepository userAvatarRepository;

    @Autowired
    private UserVipRepository userVipRepository;

    @Value("${jwt.prefix}")
    private String prefix;

    /**
     * 登录
     * @param userLoginDTO
     * @return
     */
    @Override
    public UserInfoVO login(UserLoginDTO userLoginDTO){
        //校验密码

        //匹配用户
        Map<String, Object> query = new HashMap<>();
        query.put("pre",userLoginDTO.getPre());
        query.put("phone",userLoginDTO.getPhone());
        Users users = userMapper.getUserInfo(query);
        if(users == null){
            throw new BizException(0,"用户不存在");
        }
        // 判断用户名密码是否正确
        String md5Password = md5Util.getPhpMD5(userLoginDTO.getPassword());
        if (!md5Password.equals(users.getPassword())){
            throw new BizException(0,"手机号或密码错误");
        }


        // 生成toke
        String token = jwtProvider.generateToken(users.getPhone());

        // 将token存入redis
        redisManager.set(UserConstant.USER_TOKEN_KEY_REDIS + users.getPhone(),token,604800);

        users.setToken(token);

        UserInfoVO userInfo = new UserInfoVO();
        BeanUtils.copyProperties(users,userInfo);

        //处理头像和会员信息
        if(userInfo.getAvatar() != null && !userInfo.getAvatar().equals("")){
            UsersAvatar avatar = userAvatarRepository.getUsersAvatarById(Integer.valueOf(userInfo.getAvatar()));
            userInfo.setAvatar(DomainConstant.API_IMAGE_DOMIAN + avatar.getPath());
        }
        UsersVip usersVip = userVipRepository.getUsersVipByUid(users.getUserType());
        if(usersVip != null){
            userInfo.setVipEnd(usersVip.getEnd());
        }
        userInfo.setMissionSignIn(users.getMissionSignIn() == 1);
        switch (users.getSex()){
            case 1:
                userInfo.setSex("男");
                break;
            case 2:
                userInfo.setSex("女");
                break;
            default:
                userInfo.setSex("未知");
        }
        return userInfo;
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
