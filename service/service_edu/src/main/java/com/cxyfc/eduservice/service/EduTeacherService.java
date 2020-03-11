package com.cxyfc.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxyfc.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cxyfc.eduservice.entity.vo.EduTeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author ljw
 * @since 2020-03-10
 */
public interface EduTeacherService extends IService<EduTeacher> {
    void pageQuery(Page<EduTeacher> pageParam, EduTeacherQuery teacherQuery);
}
