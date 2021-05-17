package com.awy.service.api;

import com.awy.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("USER-WEB")
public interface IUserService {

    @RequestMapping(value = "/user/findUserByUsername")
    public User findUserByUsername(@RequestParam("username") String username);

    @RequestMapping(value = "/user/getUserById")
    public User getUserById(@RequestParam("id") Integer id);
}
