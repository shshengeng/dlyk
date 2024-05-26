package com.example.dlykserver.mapper;

import com.example.dlykserver.model.DicValue;

/**
* @author zhangbaisheng
* @description 针对表【t_dic_value(字典值表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.DicValue
*/
public interface DicValueMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DicValue record);

    int insertSelective(DicValue record);

    DicValue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicValue record);

    int updateByPrimaryKey(DicValue record);

}
