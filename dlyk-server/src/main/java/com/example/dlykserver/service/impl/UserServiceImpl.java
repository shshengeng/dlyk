package com.example.dlykserver.service.impl;

import com.example.dlykserver.constant.Constants;
import com.example.dlykserver.mapper.UserMapper;
import com.example.dlykserver.model.User;
import com.example.dlykserver.query.UserQuery;
import com.example.dlykserver.service.UserService;
import com.example.dlykserver.util.JWTUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

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

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.selectById(id);
        return user;
    }

    @Override
    public int saveUser(UserQuery userQuery) {
        User user = new User();

        //将userQuery的属性复制到user里面，要属性名相同，类型要相同
        BeanUtils.copyProperties(userQuery, user);

        user.setLoginPwd(passwordEncoder.encode(userQuery.getLoginPwd()));
        user.setCreateTime(new Date());

        Integer loginUserId = JWTUtils.parseUserFromJWT(userQuery.getToken()).getId();
        user.setCreateBy(loginUserId);

        return userMapper.insertSelective(user);
    }

    @Override
    public int updateUser(UserQuery userQuery) {
        User user = new User();

        //将userQuery的属性复制到user里面，要属性名相同，类型要相同
        BeanUtils.copyProperties(userQuery, user);

        user.setEditTime(new Date());

        Integer loginUserId = JWTUtils.parseUserFromJWT(userQuery.getToken()).getId();
        user.setEditBy(loginUserId);

        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int deleteUser(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }
}
