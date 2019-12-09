package com.ue.entity;

import com.ue.annotation.Dict;

public class Student {
    private Integer id;

    private String name;

    @Dict(dictDataSource = "stu_level")
    private Integer stuLevel;

    @Dict(dictDataSource = "stu_english",dictText = "stuEnglishDictText")
    private Integer englishLevel;

    @Dict(dictDataSource = "stu_hobby")
    private String stuHobby;

    public Student(Integer id, String name, Integer stuLevel, Integer englishLevel, String stuHobby) {
        this.id = id;
        this.name = name;
        this.stuLevel = stuLevel;
        this.englishLevel = englishLevel;
        this.stuHobby = stuHobby;
    }

    public Student() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStuLevel() {
        return stuLevel;
    }

    public void setStuLevel(Integer stuLevel) {
        this.stuLevel = stuLevel;
    }

    public Integer getEnglishLevel() {
        return englishLevel;
    }

    public void setEnglishLevel(Integer englishLevel) {
        this.englishLevel = englishLevel;
    }

    public String getStuHobby() {
        return stuHobby;
    }

    public void setStuHobby(String stuHobby) {
        this.stuHobby = stuHobby;
    }
}