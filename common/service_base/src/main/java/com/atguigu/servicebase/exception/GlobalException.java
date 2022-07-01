package com.atguigu.servicebase.exception;

import com.atguigu.commutils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* 自定义全局异常处理
*/
@ControllerAdvice
@Slf4j
public class GlobalException {

    //当出现异常时,执行下面方法
    @ExceptionHandler(value = Exception.class)
    //返回数据
    @ResponseBody
    public R error(Exception e){
        //记录日志
        log.error(e.getMessage());
        return R.error().msg("全局异常处理执行了...");
    }


    //特定异常处理
    @ExceptionHandler(value = ArithmeticException.class)
    //返回数据
    @ResponseBody
    public R error(ArithmeticException e){
        //记录日志
        log.error(e.getMessage());
        return R.error().msg("ArithmeticException异常处理执行了...");
    }

    //自定义异常处理
    @ExceptionHandler(value = GuliException.class)
    //返回数据
    @ResponseBody
    public R error(GuliException e){
        //记录日志
        log.error(e.getMsg());
        return R.error().code(e.getCode()).msg(e.getMsg());
    }
}
