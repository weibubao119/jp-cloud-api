package com.jp.module.user.dao;

import com.jp.user.framework.common.dao.BaseDao;
import com.jp.user.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;

/**
* 用户模块
*
* @author xh 
* @since 1.0.0 2023-06-21
*/
@Mapper
public interface UsersDao extends BaseDao<UsersEntity> {
	
}