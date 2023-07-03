package com.jp.module.user.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
* 用户信息vo
*
* @author xh 
* @since 1.0.0 2023-06-21
*/
@Data
@Schema(description = "用户信息vo")
public class UserInfoVO{
	private static final long serialVersionUID = 1L;

	@Schema(description = "token")
	private String token;

	@Schema(description = "昵称")
	private String nickname;

	@Schema(description = "国码")
	private String pre;

	@Schema(description = "手机号")
	private String phone;

	@Schema(description = "邀请码")
	private String invite;

	@Schema(description = "积分")
	private Integer jpPoint;

	@Schema(description = "头像")
	private String avatar;

	@Schema(description = "1男2女")
	private Integer sex;

	@Schema(description = "1.已签到 0.否")
	private Integer missionSignIn;

	@Schema(description = "会员到期时间")
	private String vipEnd;
}