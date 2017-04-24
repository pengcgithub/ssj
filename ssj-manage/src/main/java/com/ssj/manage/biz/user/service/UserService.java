package com.ssj.manage.biz.user.service;

import com.ssj.manage.biz.user.model.UserBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserBean findUserById(String userId, String password);

    boolean addUser(UserBean userBean);

    UserBean findUser(String id);

    Page<UserBean> findUserByPage(UserBean userBean, Pageable pageable);

    boolean deleteUser(String id);
}
