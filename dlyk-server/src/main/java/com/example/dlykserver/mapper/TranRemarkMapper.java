package com.example.dlykserver.mapper;

import com.example.dlykserver.model.TranRemark;

/**
* @author zhangbaisheng
* @description 针对表【t_tran_remark(交易跟踪记录表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.TranRemark
*/
public interface TranRemarkMapper {

    int deleteByPrimaryKey(Long id);

    int insert(TranRemark record);

    int insertSelective(TranRemark record);

    TranRemark selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TranRemark record);

    int updateByPrimaryKey(TranRemark record);

}
