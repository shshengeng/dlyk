package com.example.dlykserver.mapper;

import com.example.dlykserver.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author zhangbaisheng
* @description 针对表【t_user(用户表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByLoginAct(String username);
}
