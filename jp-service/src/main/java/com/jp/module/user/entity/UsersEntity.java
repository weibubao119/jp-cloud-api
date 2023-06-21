package com.jp.module.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;
import java.math.BigDecimal;

/**
 * 用户模块
 *
 * @author xh 
 * @since 1.0.0 2023-06-21
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("users")
public class UsersEntity {
	/**
	* 自增ID
	*/
	@TableId
	private Integer id;

	/**
	* 昵称
	*/
	private String nickname;

	/**
	* 国码
	*/
	private String pre;

	/**
	* 手机号
	*/
	private String phone;

	/**
	* 密码
	*/
	private String password;

	/**
	* token
	*/
	private String token;

	/**
	* 邀请码
	*/
	private String invite;

	/**
	* 邀请人数
	*/
	private Integer inviteNum;

	/**
	* 每月邀请人数
	*/
	private Integer monthInviteNum;

	/**
	* 分享次数
	*/
	private Integer shareNum;

	/**
	* 积分
	*/
	private Integer jpPoint;

	/**
	* 邀请积分
	*/
	private Integer invitePoint;

	/**
	* 金钱
	*/
	private Integer jpMoney;

	/**
	* 头像
	*/
	private String avatar;

	/**
	* 1男2女
	*/
	private Integer sex;

	/**
	* 邀请者id
	*/
	private Integer bindId;

	/**
	* 修改时间
	*/
	private Integer changed;

	/**
	* 创建时间
	*/
	private Integer created;

	/**
	* 连续签到天数
	*/
	private Integer signNum;

	/**
	* 1.已签到 0.否
	*/
	private Integer missionSignIn;

	/**
	* 邀请金钱
	*/
	private BigDecimal inviteMoney;

	/**
	* 用户类型  1.jp 2.wy
	*/
	private Integer userType;

	/**
	* 渠道
	*/
	private String channel;

	/**
	* 安装IP
	*/
	private String ip;

}