package com.example.dlykserver.mapper;

import com.example.dlykserver.model.RolePermission;

/**
* @author zhangbaisheng
* @description 针对表【t_role_permission(角色权限关系表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.RolePermission
*/
public interface RolePermissionMapper {

    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

}
