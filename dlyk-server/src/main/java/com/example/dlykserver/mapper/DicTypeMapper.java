package com.example.dlykserver.mapper;

import com.example.dlykserver.model.DicType;

/**
* @author zhangbaisheng
* @description 针对表【t_dic_type(字典类型表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.DicType
*/
public interface DicTypeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(DicType record);

    int insertSelective(DicType record);

    DicType selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DicType record);

    int updateByPrimaryKey(DicType record);

}
