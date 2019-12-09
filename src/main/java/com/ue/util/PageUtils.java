package com.ue.util;

import java.io.Serializable;
import java.util.List;

public class PageUtils implements Serializable {
    private static final long serialVersionUID = 1L;
    //这是总行数
    private long total;
    //这是保持查询出来的数据
    private List<?> data;

    /**
     * @param list 保存数据
     * @param total 查到多行数据
     */
    public PageUtils(List<?> list, long total) {
        this.data = list;
        this.total = total;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}