package com.jp.module.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jp.module.user.entity.UsersEntity;
import com.jp.module.user.vo.UserInfoVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
* 用户模块
*
* @author xh 
* @since 1.0.0 2023-06-21
*/
@Mapper
public interface UserMapper extends BaseMapper<UsersEntity> {

    UserInfoVO getUserInfo(Map<String,Object> query);
}