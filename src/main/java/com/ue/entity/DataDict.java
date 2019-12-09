package com.ue.entity;

public class DataDict {
    private Integer id;

    private String datasource;

    private String description;

    public DataDict(Integer id, String datasource, String description) {
        this.id = id;
        this.datasource = datasource;
        this.description = description;
    }

    public DataDict() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}