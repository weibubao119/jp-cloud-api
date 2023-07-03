package com.jp.module.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户头像
 *
 * @author xh 
 * @since 1.0.0 2023-06-21
 */
@Data
@TableName("users_avatar")
@Entity
public class UsersAvatar implements Serializable{
	@Id
	private Integer id;

	/**
	 * 图片md5
	 */
	private String md5;

	/**
	 * 缩略图地址
	 */
	private String path;

	/**
	 * 创建时间
	 */
	private String created;
}