package com.example.dlykserver.mapper;

import com.example.dlykserver.model.Tran;

/**
* @author zhangbaisheng
* @description 针对表【t_tran(交易表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.Tran
*/
public interface TranMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Tran record);

    int insertSelective(Tran record);

    Tran selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Tran record);

    int updateByPrimaryKey(Tran record);

}
