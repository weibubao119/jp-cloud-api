package com.jp.module.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
* 用户信息vo
*
* @author xh 
* @since 1.0.0 2023-06-21
*/
@Data
@Builder
@Schema(description = "用户信息vo")
public class UserInfoVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@Schema(description = "自增ID")
	private Integer id;

	@Schema(description = "昵称")
	private String nickname;

	@Schema(description = "国码")
	private String pre;

	@Schema(description = "手机号")
	private String phone;

	@Schema(description = "密码")
	private String password;

	@Schema(description = "token")
	private String token;

	@Schema(description = "邀请码")
	private String invite;

	@Schema(description = "邀请人数")
	private Integer inviteNum;

	@Schema(description = "每月邀请人数")
	private Integer monthInviteNum;

	@Schema(description = "分享次数")
	private Integer shareNum;

	@Schema(description = "积分")
	private Integer jpPoint;

	@Schema(description = "邀请积分")
	private Integer invitePoint;

	@Schema(description = "金钱")
	private Integer jpMoney;

	@Schema(description = "头像")
	private String avatar;

	@Schema(description = "1男2女")
	private Integer sex;

	@Schema(description = "邀请者id")
	private Integer bindId;

	@Schema(description = "修改时间")
	private Integer changed;

	@Schema(description = "创建时间")
	private Integer created;

	@Schema(description = "连续签到天数")
	private Integer signNum;

	@Schema(description = "1.已签到 0.否")
	private Integer missionSignIn;

	@Schema(description = "邀请金钱")
	private BigDecimal inviteMoney;

	@Schema(description = "用户类型  1.jp 2.wy")
	private Integer userType;

	@Schema(description = "渠道")
	private String channel;

	@Schema(description = "安装IP")
	private String ip;


}