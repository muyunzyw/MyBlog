package com.ith.myblog.service.impl;

import com.ith.myblog.domain.User;
import com.ith.myblog.mapper.UserDao;
import com.ith.myblog.service.UserService;
import com.ith.myblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author muyun
 * @date 2020/4/20 - 21:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User checkUser(String username, String password) {
        return userDao.findByNameAndPwd(username, MD5Utils.code(password));
    }

}
