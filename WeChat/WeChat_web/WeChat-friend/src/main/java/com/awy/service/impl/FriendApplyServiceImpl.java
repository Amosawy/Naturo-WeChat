package com.awy.service.impl;

import com.awy.Base.BaseServiceImpl;
import com.awy.entity.FriendApply;
import com.awy.mapper.IFriendApplyMapper;
import com.awy.service.IFriendApplyService;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendApplyServiceImpl extends BaseServiceImpl<FriendApply> implements IFriendApplyService {

    @Autowired
    private IFriendApplyMapper friendApplyMapper;
    
    @Override
    public BaseMapper<FriendApply> getMapper() {
        return friendApplyMapper;
    }

    @Override
    public List<FriendApply> getMyFriendApplyList(Integer userid) {
        EntityWrapper wrapper = new EntityWrapper();
        wrapper.eq("to_id",userid);
        return friendApplyMapper.selectList(wrapper);
    }
}
