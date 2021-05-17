package com.awy.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.util.Date;

@TableName("friend_apply")
public class FriendApply implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer from_id;

    private Integer to_id;

    private String apply_msg;

    @TableField(value = "apply_time")
    private Date apply_time;

    @TableField(value = "status")
    private Integer status;

    @TableField(exist = false)
    private User friendApply;

    public FriendApply(Integer id, Integer from_id, Integer to_id, String apply_msg, Date apply_time, Integer status, User friendApply) {
        this.id = id;
        this.from_id = from_id;
        this.to_id = to_id;
        this.apply_msg = apply_msg;
        this.apply_time = apply_time;
        this.status = status;
        this.friendApply = friendApply;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getApply_msg() {
        return apply_msg;
    }

    public void setApply_msg(String apply_msg) {
        this.apply_msg = apply_msg;
    }

    public Date getApply_time() {
        return apply_time;
    }

    public void setApply_time(Date apply_time) {
        this.apply_time = apply_time;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getFriendApply() {
        return friendApply;
    }

    public void setFriendApply(User friendApply) {
        this.friendApply = friendApply;
    }

    public FriendApply(){};

}
