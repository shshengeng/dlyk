package com.example.dlykserver.mapper;

import com.example.dlykserver.model.ClueRemark;

/**
* @author zhangbaisheng
* @description 针对表【t_clue_remark(线索跟踪记录表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.ClueRemark
*/
public interface ClueRemarkMapper {

    int deleteByPrimaryKey(Long id);

    int insert(ClueRemark record);

    int insertSelective(ClueRemark record);

    ClueRemark selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ClueRemark record);

    int updateByPrimaryKey(ClueRemark record);

}
