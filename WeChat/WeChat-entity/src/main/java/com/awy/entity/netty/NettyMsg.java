package com.awy.entity.netty;

import java.io.Serializable;

public class NettyMsg implements Serializable {

    /**
     * 1.新连接
     * 2.心跳
     * 3.单聊
     * 4.正在输入
     * 5.结束输入
     * 6.挤下线
     */
    private Integer type;

    /**
     * 设备id
     */
    private String did;

    public NettyMsg(){}

    @Override
    public String toString() {
        return "NettyMsg{" +
                "type=" + type +
                ", did='" + did + '\'' +
                '}';
    }

    public NettyMsg(Integer type, String did) {
        this.type = type;
        this.did = did;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }
}
