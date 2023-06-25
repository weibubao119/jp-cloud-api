package com.jp.module.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jp.module.user.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 用户模块
*
* @author xh 
* @since 1.0.0 2023-06-21
*/
@Mapper
public interface UserMapper extends BaseMapper<UsersEntity> {
	
}