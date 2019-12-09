package com.ue.service.impl;

import com.ue.entity.Student;
import com.ue.mapper.StudentMapper;
import com.ue.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public List<Student> listPager(Student student) {
        return studentMapper.listPager(student);
    }
}