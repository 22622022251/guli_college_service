package com.atguigu.servicebase.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
* 创建自定义自动填充类,为指定属性做填充
*/
@Component
public class MyMetaObjectHandle implements MetaObjectHandler {
    //插入的时候自动填充
    @Override
    public void insertFill(MetaObject metaObject) {
        //setFieldValByName方法各个参数的意义
        //1.对哪个字段进行填充,2.对该字段进行值设定,3.元数据信息   即方法形参(直接用就行)
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
        //对版本号进行填充
        this.setFieldValByName("version",1,metaObject);
        //对isDeleted进行填充,默认创建的数据是没有被删除的
        this.setFieldValByName("isDeleted",0,metaObject);
    }

    //更新的时候自动填充
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}
