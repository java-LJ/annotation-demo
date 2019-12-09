package com.ue.entity;

public class DataItem {
    private Integer id;

    private String datasource;

    private String code;

    private String val;

    public DataItem(Integer id, String datasource, String code, String val) {
        this.id = id;
        this.datasource = datasource;
        this.code = code;
        this.val = val;
    }

    public DataItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }
}