package com.awy.service;

import com.awy.Base.BaseService;
import com.awy.entity.Friend;

import java.util.List;

public interface IFriendService extends BaseService<Friend> {
    void addFriend(Integer to_id, Integer from_id, int i);

    List<Friend> getFriendListById(Integer user_id);
}
