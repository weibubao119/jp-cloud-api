package com.jp.module.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * (UserLoginDTO)实体类
 *
 * @author xh
 * @since 2021-11-15 17:28:20
 */

@Data
@ApiModel(value = "用户服务-注册表单dto")
public class UserRegisterDTO implements Serializable {
    @ApiModelProperty(value = "pre")
    @NotBlank(message = "手机国码不能为空")
    private String pre;

    @ApiModelProperty(value = "phone")
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,6,7,8,9][0-9]{9}$", message = "手机号格式有误")
    private String phone;

    @ApiModelProperty(value = "password")
    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty(value = "msgCode")
    @NotBlank(message = "手机验证码不能为空")
    private String msgCode;
}