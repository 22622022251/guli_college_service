package com.atguigu.eduservice.controller;


import com.atguigu.commutils.R;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author cjx
 * @since 2022-06-21
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
@CrossOrigin
public class EduTeacherController {
    @Autowired
    private EduTeacherService eduTeacherService;

    //1. 查询讲师表中的所有数据
    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public R findAll(){
        //调用业务层的方法实现查询
        try {
            List<EduTeacher> list = eduTeacherService.list(null);
            return R.ok().data("items",list);
        } catch (Exception e) {
            throw new GuliException(2001,"自定义异常抛出");
        }
    }

    //2. 讲师逻辑删除
    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("delete/{id}")
    public R removeTeacher(@ApiParam(name = "id",value = "讲师id",required = true) @PathVariable("id") String id){
        boolean flag = eduTeacherService.removeById(id);
        if (flag){
            return R.ok();
        }else{
            return R.error();
        }
    }

    //3. 讲师分页查询
    //current : 当前页
    //limit : 每页记录数
    @GetMapping("getpage/{current}/{limit}")
    public R pageListTeacher(@PathVariable long current,
                             @PathVariable long limit){

        //创建分页对象
        Page<EduTeacher> page = new Page<>(current,limit);
        //调用方法实现分页
        //调用方法时,底层封装,把分页后的数据封装到page对象中
        eduTeacherService.page(page,null);

        long total = page.getTotal(); //总记录数
        List<EduTeacher> records = page.getRecords(); //每页数据集合
        return R.ok().data("total",total).data("records",records);
    }

    //4.讲师条件查询
    @PostMapping("/teacherQueryPage/{current}/{limit}")
    public R teacherQueryPage(@PathVariable long current,
                              @PathVariable long limit,
                              @RequestBody(required = false) TeacherQuery teacherQuery){
        //创建分页对象
        Page<EduTeacher> pageTeacher = new Page<>(current,limit);

        //创建查询条件对象
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //根据传入参数创建查询条件
        if (!StringUtils.isEmpty(teacherQuery.getName())){
            wrapper.like("name",teacherQuery.getName());
        }
        if (!StringUtils.isEmpty(teacherQuery.getLevel())){
            wrapper.eq("level",teacherQuery.getLevel());
        }
        if (!StringUtils.isEmpty(teacherQuery.getStart())){
            wrapper.ge("gmt_create",teacherQuery.getStart());
        }
        if (!StringUtils.isEmpty(teacherQuery.getEnd())){
            wrapper.le("gmt_create",teacherQuery.getEnd());
        }
        //排序
        wrapper.orderByDesc("gmt_create");
        eduTeacherService.page(pageTeacher, wrapper);
        //执行查询 ,查询的结果被放进了查询条件pageTeacher对象的records属性内
        List<EduTeacher> records = pageTeacher.getRecords();//分页记录集合
        long total = pageTeacher.getTotal(); //总记录数
        return R.ok().data("total",total).data("records",records);
    }

    //5.讲师添加
    @PostMapping("add")
    public R addTeacher(@RequestBody(required = false) EduTeacher eduTeacher){
        eduTeacherService.save(eduTeacher);
        return R.ok();
    }

    //6.1讲师修改回显
    @GetMapping("getTeacher/{id}")
    public R getTeacherById(@PathVariable long id){
        EduTeacher teacher = eduTeacherService.getById(id);
        return R.ok().data("teacher",teacher);
    }
    //6.2 修改数据存储
    @PostMapping("update")
    public R updateTeacher(@RequestBody(required = false) EduTeacher eduTeacher){
        boolean flag = eduTeacherService.updateById(eduTeacher);
        if (flag){
            return R.ok();
        }else{
            return R.error();
        }
    }
}

