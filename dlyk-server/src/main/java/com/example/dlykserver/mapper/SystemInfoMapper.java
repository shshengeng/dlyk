package com.example.dlykserver.mapper;

import com.example.dlykserver.model.SystemInfo;

/**
* @author zhangbaisheng
* @description 针对表【t_system_info(系统信息表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.SystemInfo
*/
public interface SystemInfoMapper {

    int deleteByPrimaryKey(Long id);

    int insert(SystemInfo record);

    int insertSelective(SystemInfo record);

    SystemInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SystemInfo record);

    int updateByPrimaryKey(SystemInfo record);

}
