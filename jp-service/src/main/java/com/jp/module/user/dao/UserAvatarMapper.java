package com.jp.module.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jp.module.user.entity.UsersAvatar;
import org.apache.ibatis.annotations.Mapper;


/**
* 用户头像
*
* @author xh 
* @since 1.0.0 2023-06-21
*/
@Mapper
public interface UserAvatarMapper extends BaseMapper<UsersAvatar> {
}