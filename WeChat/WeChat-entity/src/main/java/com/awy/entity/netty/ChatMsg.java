package com.awy.entity.netty;

public class ChatMsg extends NettyMsg{

    private Integer from_id;
    private Integer to_id;
    private String msg;

    public ChatMsg(Integer from_id, Integer to_id, String msg) {
        this.from_id = from_id;
        this.to_id = to_id;
        this.msg = msg;
    }

    public ChatMsg(Integer type, String did, Integer from_id, Integer to_id, String msg) {
        super(type, did);
        this.from_id = from_id;
        this.to_id = to_id;
        this.msg = msg;
    }

    public Integer getFrom_id() {
        return from_id;
    }

    public void setFrom_id(Integer from_id) {
        this.from_id = from_id;
    }

    public Integer getTo_id() {
        return to_id;
    }

    public void setTo_id(Integer to_id) {
        this.to_id = to_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
