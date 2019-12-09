package com.ue.mapper;

import com.ue.entity.DataItem;
import org.springframework.stereotype.Repository;

@Repository
public interface DataItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DataItem record);

    int insertSelective(DataItem record);

    DataItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DataItem record);

    int updateByPrimaryKey(DataItem record);

    /**
     * 根据datasource与code查询字典的方法
     * @author LiJun
     * @Date 2019/11/28
     * @Time 10:40
     * @param dataItem
     * @return com.ue.entity.DataItem
     */
    DataItem selectByDatasourceCode(DataItem dataItem);
}