package com.ue.controller;

import com.ue.entity.Student;
import com.ue.service.StudentService;
import com.ue.util.PageBean;
import com.ue.util.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping("/listPager")
    public PageUtils listPager(Student student, HttpServletRequest req){
        PageBean pageBean = new PageBean();
        pageBean.setRequest(req);
        List<Student> list = this.studentService.listPager(student);
        pageBean.setTotal(list.size());
        PageUtils pageUtils = new PageUtils(list,pageBean.getTotal());
        return pageUtils;
    }
}