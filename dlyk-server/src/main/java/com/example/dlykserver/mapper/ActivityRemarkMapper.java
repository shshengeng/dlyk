package com.example.dlykserver.mapper;

import com.example.dlykserver.model.ActivityRemark;

/**
* @author zhangbaisheng
* @description 针对表【t_activity_remark(市场活动备注表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.ActivityRemark
*/
public interface ActivityRemarkMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ActivityRemark record);

    int insertSelective(ActivityRemark record);

    ActivityRemark selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ActivityRemark record);

    int updateByPrimaryKey(ActivityRemark record);

}
