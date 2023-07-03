package com.jp.module.user.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.baomidou.mybatisplus.annotation.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * 用户模块
 *
 * @author xh 
 * @since 1.0.0 2023-06-21
 */
@Data
@TableName("users")
@Entity
public class Users implements Serializable {
	@Id
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
	 * 邀请人数(一级)
	 */
	private Integer inviteNum;

	/**
	 * 邀请人数(二级)
	 */
	private Integer inviteNumSecond;

	/**
	 * 每月邀请人数
	 */
	private Integer monthInviteNum;

	/**
	 * 分享次数
	 */
	private Integer shareNum;

	/**
	 * 任务积分
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
	 * 已提现金额
	 */
	private Integer withdrawNum;

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
	private Integer inviteMoney;

	/**
	 * 用户类型 1.jp 2.wy 3.虚拟用户
	 */
	private Integer userType;

	/**
	 * 渠道号
	 */
	private String channel;

	/**
	 * 安装ip
	 */
	private String ip;

	/**
	 * 是否删除 1.是
	 */
	private Integer isDelete;

	/**
	 * 邮箱
	 */
	private String mail;

	/**
	 * 客户端类型 1.安卓 2.ios 3.pc
	 */
	private Integer client;
}