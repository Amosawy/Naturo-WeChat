package com.awy.service.impl;

import com.awy.Base.BaseServiceImpl;
import com.awy.entity.Friend;
import com.awy.mapper.IFriendMapper;
import com.awy.service.IFriendService;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendServiceImpl extends BaseServiceImpl<Friend> implements IFriendService {

    @Autowired
    private IFriendMapper friendMapper;

    @Override
    public BaseMapper<Friend> getMapper() {
        return friendMapper;
    }

    @Override
    public void addFriend(Integer to_id, Integer from_id, int i) {
        Friend friend1=new Friend();
        friend1.setUser_id(to_id);
        friend1.setFriend_id(from_id);
        friend1.setStatus(1);
        if(!isFriend(friend1)){ //不是好友才可以插入.
            friendMapper.insert(friend1);
        }
        Friend friend2=new Friend();
        friend2.setUser_id(from_id);
        friend2.setFriend_id(to_id);
        friend2.setStatus(1);
        if(!isFriend(friend2)){
            friendMapper.insert(friend2);
        }
    }

    @Override
    public List<Friend> getFriendListById(Integer user_id) {
        EntityWrapper wrapper=new EntityWrapper();
        wrapper.eq("user_id",user_id);
        return friendMapper.selectList(wrapper);
    }

    public boolean isFriend(Friend friend){
        return friendMapper.selectOne(friend) !=null;
    }
}
