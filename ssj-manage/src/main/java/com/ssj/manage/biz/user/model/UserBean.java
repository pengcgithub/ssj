package com.ssj.manage.biz.user.model;

import com.wordnik.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "t_user")
public class UserBean {

    @ApiModelProperty(value = "主键")
    @Id
    @Column(name = "userId", nullable = false, length = 40)
    private String userId;

    @ApiModelProperty(value = "用户名称")
    @Column(name = "userName", length = 50)
    private String userName;

    @ApiModelProperty(value = "密码")
    @Column(name = "password", length = 50)
    private String password;

}
