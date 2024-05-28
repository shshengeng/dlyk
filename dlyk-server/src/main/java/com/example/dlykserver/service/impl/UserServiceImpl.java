package com.example.dlykserver.service.impl;

import com.example.dlykserver.mapper.UserMapper;
import com.example.dlykserver.model.User;
import com.example.dlykserver.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByLoginAct(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
