package com.atguigu.commutils;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
* 统一结果返回格式
 * @author 陈敬莘
*/
@Data
public class R {
    private String msg;
    private int code;
    private Map<String,Object> data = new HashMap<>();
    private boolean success;

    private R() {
    }

    public static R ok(){
        R r = new R();
        r.setCode(ResultCode.SUCCESS.getCode());
        r.setSuccess(true);
        HashMap data = new HashMap();
        r.setMsg("成功");
        return r;
    }

    public static R error(){
        R r = new R();
        r.setCode(ResultCode.ERROR.getCode());
        r.setSuccess(false);
        r.setMsg("失败");
        return r;
    }

    public R data(String key,Object value){
        this.data.put(key,value);
        return this;
    }

    public R code(Integer code){
        this.setCode(code);
        return this;
    }

    public R msg(String msg){
        this.setMsg(msg);
        return this;
    }

    public R data(Map<String,Object> data){
        this.setData(data);
        return this;
    }
}
