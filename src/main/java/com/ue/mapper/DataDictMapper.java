package com.ue.mapper;

import com.ue.entity.DataDict;
import org.springframework.stereotype.Repository;

@Repository
public interface DataDictMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataDict record);

    int insertSelective(DataDict record);

    DataDict selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataDict record);

    int updateByPrimaryKey(DataDict record);
}