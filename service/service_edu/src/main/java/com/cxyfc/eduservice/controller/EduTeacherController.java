package com.cxyfc.eduservice.controller;


import com.cxyfc.eduservice.entity.EduTeacher;
import com.cxyfc.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author ljw
 * @since 2020-03-10
 */
@RestController
@RequestMapping("/eduservice/eduteacher")
public class EduTeacherController {

    //引入service
    @Autowired
    EduTeacherService teacherService;
    //查询所有讲师
    @GetMapping
    public List<EduTeacher> getAllTeacher(){
        List<EduTeacher> list = teacherService.list(null);
        return list;
    }

}

