package com.awy.entity.base;


public class ResultEntity {

    private static final String SUCCESS = "ok";
    private static final String ERROR = "error";
    private static final String NO_MSG = null;

    private String code; //提示信息码

    private String msg; //提示信息

    private Object data;

    public ResultEntity(String code,String msg,Object data){
        this.code=code;
        this.msg=msg;
        this.data=data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * 成功没有数据
     * @return
     */
    public static ResultEntity success(){
        return new ResultEntity(SUCCESS,NO_MSG,null);
    }

    /**
     * 成功有数据
     * @param data
     * @return
     */
    public static ResultEntity success(Object data){
        return new ResultEntity(SUCCESS,NO_MSG,data);
    }

    /**
     * 错误有信息
     * @param msg
     * @return
     */
    public static ResultEntity error(String msg){
        return new ResultEntity(ERROR,msg,null);
    }
}
