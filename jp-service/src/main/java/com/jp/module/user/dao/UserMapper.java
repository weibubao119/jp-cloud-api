package com.jp.module.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jp.module.user.entity.Users;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
* 用户模块
*
* @author xh 
* @since 1.0.0 2023-06-21
*/
@Mapper
public interface UserMapper extends BaseMapper<Users> {

    Users getUserInfo(Map<String,Object> query);
}