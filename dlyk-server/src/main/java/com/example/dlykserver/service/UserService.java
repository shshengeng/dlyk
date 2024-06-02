package com.example.dlykserver.service;

import com.example.dlykserver.model.User;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    PageInfo<User> getUserByPage(Integer current);
}
