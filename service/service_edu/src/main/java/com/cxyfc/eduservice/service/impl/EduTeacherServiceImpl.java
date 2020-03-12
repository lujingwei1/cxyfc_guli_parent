package com.cxyfc.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxyfc.eduservice.entity.EduTeacher;
import com.cxyfc.eduservice.entity.vo.EduTeacherQuery;
import com.cxyfc.eduservice.mapper.EduTeacherMapper;
import com.cxyfc.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author ljw
 * @since 2020-03-10
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {
    /**
     *
     * @param pageParam 分页信息 结束后会封装进去，无需返回值
     * @param teacherQuery  条件
     */
    @Override
    public void pageQuery(Page<EduTeacher> pageParam, EduTeacherQuery teacherQuery) {
        //构建条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();
        //按照字段排序
        queryWrapper.orderByAsc("sort");

        //是否有条件
        if (teacherQuery == null){
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }

        //有条件 分别取出来
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();

        //分别判断 具体用到的条件
        if (!StringUtils.isEmpty(name)) {
            //name 模糊查询
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level) ) {
            // level 精确匹配 头衔 1高级讲师 2首席讲师
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            //gmt_create 创建时间 在某个时间  begin  之后
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            //gmt_create 创建时间 在某个时间  end  之前
            queryWrapper.le("gmt_create", end);
        }

        baseMapper.selectPage(pageParam, queryWrapper);
    }
}
