package com.ssj.manage.biz.user.service;

import com.ssj.manage.biz.user.model.UserBean;

public interface UserService {

    UserBean findUserById(String userId, String password);

    boolean addUser(UserBean userBean);

    UserBean findUser(String id);
}
