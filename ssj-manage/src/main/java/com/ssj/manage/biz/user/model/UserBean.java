package com.ssj.manage.biz.user.model;

import com.wordnik.swagger.annotations.ApiModelProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
