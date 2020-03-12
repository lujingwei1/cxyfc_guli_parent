package com.cxyfc.eduservice.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cxyfc.eduservice.entity.EduTeacher;
import com.cxyfc.eduservice.entity.vo.EduTeacherQuery;
import com.cxyfc.eduservice.service.EduTeacherService;
import com.cxyfc.servicebase.handler.GuliException;
import com.cxyfc.utils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @ApiOperation("查询所有讲师")
    @GetMapping
    public R getAllTeacher() {
        List<EduTeacher> list = teacherService.list(null);
        return R.ok().data("items", list);
    }

    //讲师逻辑删除
    @ApiOperation("讲师逻辑删除")
    @DeleteMapping("{id}")
    public R deleteTeacherById(@PathVariable String id) {
        boolean remove = teacherService.removeById(id);
        return R.ok();
    }

    //添加讲师
    @ApiOperation("添加讲师")
    //@PostMapping("addTeacher")
    @PostMapping
    public R addTeacher(@RequestBody EduTeacher teacher) {
        boolean save = teacherService.save(teacher);
        if (save) {
            return R.ok();
        } else {
            return R.error();
        }
    }

    //分页讲师列表
    @Deprecated
    @ApiOperation(value = "分页讲师列表")
    @GetMapping("getTeacherPage/{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit ) {
        //创建分页对象
        Page<EduTeacher> pageParam = new Page<>(page, limit);

        //返回的分页结果会直接封装到参数 pageParam 中，无需接收返回值
        //不要条件
        teacherService.page(pageParam, null);
        //总页数
        long total = pageParam.getTotal();
        //返回的列表
        List<EduTeacher> records = pageParam.getRecords();
        try {
            int a = 10/0;
        }catch(Exception e) {
            throw new GuliException(20001,"出现了除以0异常（告诉前台的应该是服务器繁忙。。。）");
        }


        return R.ok().data("total", total).data("items", records);
    }

    //带条件分页讲师列表
    @ApiOperation(value = "带条件分页查询讲师列表")
    //条件查询 需要是Post请求
    @PostMapping("getTeacherPageVo/{page}/{limit}")
    public R getTeacherPageVo(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody EduTeacherQuery query) {
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        //把业务代码放到service层
        teacherService.pageQuery(pageParam, query);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return R.ok().data("total", total).data("items", records);
    }

    //通过id查询讲师
    @ApiOperation(value = "通过id查询讲师")
    //条件查询 需要是Post请求
    @GetMapping("getTeacherById/{id}")
    public R getTeacherById(@PathVariable Integer id){
        EduTeacher teacher = teacherService.getById(id);
        return R.ok().data("itam",teacher);
    }

    //修改讲师
    @ApiOperation(value = "修改讲师")
    //@PutMapping("{id}")
    @PutMapping
    public R updateTeacherById(
           // @ApiParam(name = "id", value = "讲师ID", required = true)
           // @PathVariable String id,

            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher teacher){

        //teacher.setId(id);
        teacherService.updateById(teacher);
        return R.ok();
    }
}

