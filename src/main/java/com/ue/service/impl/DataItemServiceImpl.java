package com.ue.service.impl;

import com.ue.entity.DataItem;
import com.ue.mapper.DataItemMapper;
import com.ue.service.DataItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataItemServiceImpl implements DataItemService {
    @Autowired
    private DataItemMapper dataItemMapper;

    @Override
    public String selectByDatasourceKey(String dataSource, String key) {
        DataItem dataItem = new DataItem();
        dataItem.setDatasource(dataSource);
        dataItem.setCode(key);
        return dataItemMapper.selectByDatasourceCode(dataItem).getVal();
    }
}