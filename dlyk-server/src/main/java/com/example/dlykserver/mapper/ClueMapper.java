package com.example.dlykserver.mapper;

import com.example.dlykserver.model.Clue;

/**
* @author zhangbaisheng
* @description 针对表【t_clue(线索表)】的数据库操作Mapper
* @createDate 2024-05-19 00:33:17
* @Entity com.example.dlykserver.model.Clue
*/
public interface ClueMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Clue record);

    int insertSelective(Clue record);

    Clue selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Clue record);

    int updateByPrimaryKey(Clue record);

}
