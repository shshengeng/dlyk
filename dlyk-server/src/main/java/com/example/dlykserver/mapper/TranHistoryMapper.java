package com.example.dlykserver.mapper;

import com.example.dlykserver.model.TranHistory;

/**
* @author zhangbaisheng
* @description 针对表【t_tran_history(交易历史记录表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.TranHistory
*/
public interface TranHistoryMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TranHistory record);

    int insertSelective(TranHistory record);

    TranHistory selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TranHistory record);

    int updateByPrimaryKey(TranHistory record);

}
