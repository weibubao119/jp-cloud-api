package com.jp.module.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户会员
 *
 * @author xh 
 * @since 1.0.0 2023-06-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("users_vip")
public class UsersVip {
	@TableId
	private Integer id;

	/**
	 * 用户ID
	 */
	private Integer uid;

	/**
	 * 到期时间
	 */
	private String end;

	/**
	 * 创建时间
	 */
	private Integer created;
}