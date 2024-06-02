package com.example.dlykserver.service.impl;

import com.example.dlykserver.constant.Constants;
import com.example.dlykserver.mapper.UserMapper;
import com.example.dlykserver.model.User;
import com.example.dlykserver.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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


    @Override
    public PageInfo<User> getUserByPage(Integer current) {
        //1.设置PageHelper
        PageHelper.startPage(current, Constants.PAGE_SIZE); //10
        //2.查询
        List<User> userList = userMapper.selectUsersByPage();
        //3.封装分页数据到PageInfo
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        return pageInfo;
    }
}
