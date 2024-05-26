package com.example.dlykserver.mapper;

import com.example.dlykserver.model.CustomerRemark;

/**
* @author zhangbaisheng
* @description 针对表【t_customer_remark(客户跟踪记录表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.CustomerRemark
*/
public interface CustomerRemarkMapper {

    int deleteByPrimaryKey(Long id);

    int insert(CustomerRemark record);

    int insertSelective(CustomerRemark record);

    CustomerRemark selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CustomerRemark record);

    int updateByPrimaryKey(CustomerRemark record);

}
