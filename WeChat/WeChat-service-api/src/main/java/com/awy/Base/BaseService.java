package com.awy.Base;

import com.baomidou.mybatisplus.plugins.Page;
import java.util.List;

public interface BaseService<T> {
    int insert(T var1);

    int update(T var1);

    T selectById(Integer var1);

    int deleteById(Integer var1);

    List<T> getList();

    void getPage(Page<T> var1);

    Page<T> getDubboPage(Page<T> var1);
}
