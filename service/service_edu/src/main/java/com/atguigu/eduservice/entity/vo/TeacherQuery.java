package com.atguigu.eduservice.entity.vo;


import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.util.Date;

@Data
public class TeacherQuery {

    @ApiParam("讲师姓名")
    private String name;
    @ApiParam("讲师级别")
    private Integer level;
    @ApiParam("讲师注册时间")
    private Date start;
    @ApiParam("讲师结束时间")
    private Date end;
}
