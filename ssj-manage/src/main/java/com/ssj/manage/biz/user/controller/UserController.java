package com.ssj.manage.biz.user.controller;

import com.ssj.manage.biz.user.model.UserBean;
import com.ssj.manage.biz.user.service.UserService;
import com.wordnik.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据id查询用户信息", httpMethod = "GET", response = String.class,
            notes = "根据id查询用户信息")
    @RequestMapping(value = "getUser", method = RequestMethod.GET)
    public @ResponseBody
    UserBean getUser(@RequestParam(value = "id") String id, @RequestParam(value = "password") String password) {
        return userService.findUserById(id, password);
    }

    @ApiOperation(value = "增加用户信息", httpMethod = "POST", response = UserBean.class,
            notes = "增加用户信息")
    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public @ResponseBody boolean addUser(@RequestBody UserBean userBean) {
        return userService.addUser(userBean);
    }

    @ApiOperation(value = "删除用户信息", httpMethod = "POST", response = UserBean.class,
            notes = "删除用户信息")
    @RequestMapping(value = "deleteUser/by/{id}", method = RequestMethod.POST)
    public @ResponseBody boolean deleteUser(@PathVariable String id) {
        return true;
    }

    @ApiOperation(value = "根据id查询用户信息", httpMethod = "GET", response = String.class,
            notes = "反正就是根据id查询用户信息")
    @RequestMapping(value = "getUserById", method = RequestMethod.GET)
    public @ResponseBody
    UserBean getUserById(@RequestParam(value = "id") String id) {
        return userService.findUser(id);
    }

    @ApiOperation(value = "分页操作", httpMethod = "POST", response = UserBean.class,
            notes = "分页操作")
    @RequestMapping(value = "getUserByPage", method = RequestMethod.POST)
    public @ResponseBody Page<UserBean> findUserByPage(@RequestBody UserBean userBean, @PageableDefault(value = 15) Pageable pageable) {
        Page<UserBean> pageList = userService.findUserByPage(userBean, pageable);
        return pageList;
    }




}
