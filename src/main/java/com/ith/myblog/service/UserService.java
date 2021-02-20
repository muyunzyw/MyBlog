package com.ith.myblog.service;

import com.ith.myblog.domain.User;

/**
 * @author muyun
 * @date 2020/4/20 - 21:03
 */
public interface UserService {
    User checkUser(String username, String password);
}
