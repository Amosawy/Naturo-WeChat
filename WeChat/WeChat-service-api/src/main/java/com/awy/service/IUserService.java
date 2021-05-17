package com.awy.service;

import com.awy.Base.BaseService;
import com.awy.entity.User;

public interface IUserService extends BaseService<User> {
    Integer register(User user);

    User getUserByUsername(String username);


}
