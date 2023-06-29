package com.jp.module.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * (UserLoginDTO)实体类
 *
 * @author xh
 * @since 2021-11-15 17:28:20
 */

@Data
@ApiModel(value = "用户服务-登陆表单dto")
public class UserLoginDTO implements Serializable {
    @ApiModelProperty(value = "pre")
    @NotBlank(message = "手机国码不能为空")
    private String pre;

    @ApiModelProperty(value = "phone")
    @NotBlank(message = "手机号不能为空")
    private String phone;

    @ApiModelProperty(value = "password")
    @NotBlank(message = "密码不能为空")
    private String password;
}