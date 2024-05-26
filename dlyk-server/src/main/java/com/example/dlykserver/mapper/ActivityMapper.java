package com.example.dlykserver.mapper;

import com.example.dlykserver.model.Activity;

/**
* @author zhangbaisheng
* @description 针对表【t_activity(市场活动表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.Activity
*/
public interface ActivityMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

}
