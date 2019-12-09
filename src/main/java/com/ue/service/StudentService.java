package com.ue.service;

import com.ue.entity.Student;
import com.ue.util.PageBean;

import java.util.List;

public interface StudentService {
    List<Student> listPager(Student student);
}