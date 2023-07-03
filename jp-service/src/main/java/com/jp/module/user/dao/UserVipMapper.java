package com.jp.module.user.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jp.module.user.entity.UsersVip;
import org.apache.ibatis.annotations.Mapper;


/**
* 用户会员
*
* @author xh 
* @since 1.0.0 2023-06-21
*/
@Mapper
public interface UserVipMapper extends BaseMapper<UsersVip> {
}