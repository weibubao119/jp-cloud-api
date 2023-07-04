package com.jp.module.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户会员
 *
 * @author xh 
 * @since 1.0.0 2023-06-21
 */
@Data
@TableName("users_vip")
@Entity
public class UsersVip implements Serializable {
	@Id
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