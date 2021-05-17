package com.awy.Base;

import com.awy.Base.BaseService;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {
    public BaseServiceImpl(){
    }

    public abstract BaseMapper<T> getMapper();

    @Override
    public int insert(T var1) {
        return this.getMapper().insert(var1).intValue();
    }

    @Override
    public int update(T var1) {
        return this.getMapper().updateById(var1).intValue();
    }

    @Override
    public T selectById(Integer var1) {
        return this.getMapper().selectById(var1);
    }

    @Override
    public int deleteById(Integer var1) {
        return this.getMapper().deleteById(var1).intValue();
    }

    @Override
    public List<T> getList() {
        return this.getMapper().selectList((Wrapper) null);
    }

    @Override
    public void getPage(Page<T> page) {
        List<T> list=this.getMapper().selectPage(page,(Wrapper) null);
        page.setRecords(list);
    }

    @Override
    public Page<T> getDubboPage(Page<T> page) {
        List<T> list=this.getMapper().selectPage(page,(Wrapper) null);
        page.setRecords(list);
        return page;
    }
}
