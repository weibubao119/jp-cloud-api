package com.jp.user.entity;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * (MdxUser)实体类
 *
 * @author makejava
 * @since 2021-11-15 17:28:20
 */

@Data
@Entity
public class MdxUser implements Serializable{
    private static final long serialVersionUID = -60169267226405941L;
    /**
    * 用户id
    */
    @Id
    private Long userId;
    /**
    * 用户名
    */
    private String userName;
    /**
    * 密码
    */
    private String password;
    /**
    * 昵称
    */
    private String nick;
    /**
    * 手机号
    */
    private String phone;
    /**
    * 电子邮件
    */
    private String email;
    /**
    * 状态 0 启用 1禁用
    */
    private Integer status;
    /**
    * 性别 0 男 1 女
    */
    private Integer sex;
    /**
    * 个人描述
    */
    private String remarks;
    /**
    * 上次登录时间
    */
    private Date lastLoginTime;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 修改时间
    */
    private Date updateTime;
}