package com.example.dlykserver.service;

import com.example.dlykserver.model.User;
import com.example.dlykserver.query.UserQuery;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    PageInfo<User> getUserByPage(Integer current);

    User getUserById(Integer id);

    int saveUser(UserQuery userQuery);

    int updateUser(UserQuery userQuery);

    int deleteUser(Long id);

}
