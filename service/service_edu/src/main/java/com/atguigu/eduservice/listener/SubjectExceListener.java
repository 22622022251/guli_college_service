package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

public class SubjectExceListener extends AnalysisEventListener<SubjectData> {
    //因为不能交给spring管理,所以要手动new对象
    //但是这样就不能使用autowrid注入,所以选择有参构造传递

    private EduSubjectService eduSubjectService;

    public SubjectExceListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    public SubjectExceListener() {
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null){
            throw new GuliException(20001,"文件数据为空");
        }
        EduSubject oneSubject = this.execOneSubject(eduSubjectService, subjectData.getOneSubjectName());
        if (oneSubject == null){
            //如果不存在则添加一级分类数据
            oneSubject = new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            eduSubjectService.save(oneSubject);
        }

        //添加二级分类
        String pid = oneSubject.getId();
        EduSubject twoSubject = this.execTwoSubject(eduSubjectService,subjectData.getTwoSubjectName(),pid);
        if (twoSubject == null){
            //如果不存在则添加二级分类数据
            twoSubject = new EduSubject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            eduSubjectService.save(twoSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }

    //根据一级分类的名称和pid查询是否存在该一级分类对象
    private EduSubject execOneSubject(EduSubjectService eduSubjectService,String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        return eduSubjectService.getOne(wrapper);
    }

    //根据二级分类的名称和pid查询是否存在该一级分类对象
    private EduSubject execTwoSubject(EduSubjectService eduSubjectService,String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        return eduSubjectService.getOne(wrapper);
    }
}
