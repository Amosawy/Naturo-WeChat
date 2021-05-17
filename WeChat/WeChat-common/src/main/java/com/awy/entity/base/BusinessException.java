package com.awy.entity.base;

public class BusinessException extends RuntimeException{
    private String code; //错误码
    private String msg; //错误信息

    public BusinessException(){}
    public BusinessException(String code,String msg){
        this.code=code;
        this.msg=msg;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
