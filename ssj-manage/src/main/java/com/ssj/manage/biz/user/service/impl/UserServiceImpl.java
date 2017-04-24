package com.ssj.manage.biz.user.service.impl;

import com.ssj.manage.biz.user.model.UserBean;
import com.ssj.manage.biz.user.repositories.UserDao;
import com.ssj.manage.biz.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Override
    public Page<UserBean> findUserByPage(UserBean userBean, Pageable pageable) {
        return userDao.findUserByPage(userBean, pageable);
    }

    @Override
    public boolean deleteUser(String id) {
        boolean isDelete = true;
        try {
            userDao.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            isDelete = false;
        }
        return isDelete;
    }
}
