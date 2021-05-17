package com.awy.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

@TableName("friend")
public class Friend {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer user_id;

    private Integer friend_id;

    private String remark;

    private Integer status;

    @TableField(exist = false)
    private User user;

    public Friend(){}

    public Friend(Integer id, Integer user_id, Integer friend_id, String remark, Integer status, User user) {
        this.id = id;
        this.user_id = user_id;
        this.friend_id = friend_id;
        this.remark = remark;
        this.status = status;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(Integer friend_id) {
        this.friend_id = friend_id;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
