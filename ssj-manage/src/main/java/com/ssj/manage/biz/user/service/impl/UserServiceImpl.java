package com.ssj.manage.biz.user.service.impl;

import com.ssj.manage.biz.user.model.UserBean;
import com.ssj.manage.biz.user.repositories.UserDao;
import com.ssj.manage.biz.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserBean findUserById(String userId, String password) {
        UserBean userBean = userDao.queryUserById(userId, password);
        return userBean;
    }

    @Override
    public boolean addUser(UserBean userBean) {
        boolean isAddUser;
        try {
            userDao.save(userBean);
            isAddUser = true;
        }catch (Exception e) {
            e.printStackTrace();
            isAddUser = true;
        }
        return isAddUser;
    }

    @Override
    public UserBean findUser(String id) {
        UserBean userBean = userDao.queryById(id);
        return userBean;
    }
}
