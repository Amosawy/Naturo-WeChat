package com.awy.service;

import com.awy.Base.BaseService;
import com.awy.entity.FriendApply;

import java.util.List;

public interface IFriendApplyService extends BaseService<FriendApply> {
    List<FriendApply> getMyFriendApplyList(Integer uid);
}
