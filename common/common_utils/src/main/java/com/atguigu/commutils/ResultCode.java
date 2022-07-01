package com.atguigu.commutils;


/**
* 状态码枚举类
 * @author 陈敬莘
*/
public enum ResultCode {
    SUCCESS(20000),
    ERROR(20001);

    private int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
