package com.ue.service;

import org.springframework.cache.annotation.Cacheable;

public interface DataItemService {
    /**
     * 将查询出来的字典文本值存到Redis里
     * @author LiJun
     * @Date 2019/11/28
     * @Time 10:40
     * @param dataSource
     * @param key
     * @return java.lang.String
     */
    @Cacheable(value = "my-redis-cache2")
    String selectByDatasourceKey(String dataSource, String key);
}