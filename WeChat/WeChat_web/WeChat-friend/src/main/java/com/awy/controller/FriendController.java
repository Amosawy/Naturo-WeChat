package com.awy.controller;


import com.awy.entity.Friend;
import com.awy.entity.FriendApply;
import com.awy.entity.User;
import com.awy.entity.base.ResultEntity;
import com.awy.service.IFriendApplyService;
import com.awy.service.IFriendService;
import com.awy.service.api.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/friend")
public class FriendController {

    @Autowired
    private IFriendApplyService friendApplyService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IFriendService friendService;

    @RequestMapping(value = "/addFriendApply")
    public ResultEntity addFriendApply(FriendApply friendApply,String username){
        //根据username查询
        User user = userService.findUserByUsername(username);
        if(user!=null){
            friendApply.setTo_id(user.getId());
            friendApply.setApply_time(new Date());
            friendApply.setStatus(1);
            friendApplyService.insert(friendApply);
            return ResultEntity.success();
        }else{
           return ResultEntity.error("没有找到"+username);
        }
    }

    @RequestMapping(value = "/getMyFriendApplyList")
    public ResultEntity getMyFriendApplyList(Integer userid){
        List<FriendApply> friendApplyList = friendApplyService.getMyFriendApplyList(userid);
        for(FriendApply friendApply:friendApplyList){
            //通过from_id查询发起添加好友的用户并set到friendApply中
            Integer from_id = friendApply.getFrom_id();
            User user = userService.getUserById(from_id);
            user.setPassword(null);
            friendApply.setFriendApply(user);
        }
        return ResultEntity.success(friendApplyList);
    }

    @RequestMapping(value = "updateFriendApplyStatus")
    public ResultEntity updateFriendApplyStatus(Integer id,Integer status){
        FriendApply friendApply=friendApplyService.selectById(id);
        friendApply.setStatus(status);
        friendApplyService.update(friendApply);
        if(status == 2){ //同意好友申请
            //添加好友表
            friendService.addFriend(friendApply.getTo_id(),friendApply.getFrom_id(),1);
        }
        return ResultEntity.success();
    }

    @RequestMapping(value = "getFriendListById")
    public ResultEntity getFriendListById(Integer user_id){
        List<Friend> friendList = friendService.getFriendListById(user_id);
        for(Friend friend : friendList){
            friend.setUser(userService.getUserById(friend.getFriend_id()));
        }
        return ResultEntity.success(friendList);
    }

}
