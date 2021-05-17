package com.awy.controller;

import com.awy.entity.User;
import com.awy.entity.base.BusinessException;
import com.awy.entity.base.ResultEntity;
import com.awy.entity.netty.ShutDownMsg;
import com.awy.service.IUserService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/register")
    public ResultEntity register(User user){
        Integer num=userService.register(user);
        if(num == 1){
            return ResultEntity.error("用户名已存在");
        }else if(num == 2){
            return ResultEntity.error("手机号已被注册");
        }
        return ResultEntity.success();
    }

    /**
     *
     * @param username
     * @param password
     * @param did 设备id
     * @return
     */
    @RequestMapping(value = "/login")
    public ResultEntity login(String username,String password,String did){
        User user=userService.getUserByUsername(username);
        if(user != null && user.getPassword().equals(password)){
            user.setPassword(null);// 密码不能写到手机中
            String redisDid=redisTemplate.opsForValue().get(user.getId().toString());
            if(redisDid != null && !redisDid.equals(did)){ //之前登陆过并且不是同一个设备
                //挤下其他的设备
                ShutDownMsg shutDownMsg=new ShutDownMsg();
                shutDownMsg.setDid(redisDid);
                rabbitTemplate.convertAndSend("ws_exchange","",shutDownMsg);
            }

            // 登录成功后保存用户id和设备id
            redisTemplate.opsForValue().set(user.getId().toString(),did);
            return ResultEntity.success(user);
        }else{
            return ResultEntity.error("用户名或密码错误");
        }
    }

    @RequestMapping(value = "/getUserByUsername")
    public ResultEntity getUserByUsername(String username){
        User user = userService.getUserByUsername(username);
        if(user!=null){
            return ResultEntity.success(user);
        }
        return ResultEntity.success(null);
    }

    @RequestMapping(value = "/findUserById")
    public ResultEntity findUserById(Integer id){
        User user=userService.selectById(id);
        return ResultEntity.success(user);
    }

    /**
     * 供其他微服务调用
     * @param id
     * @return
     */
    @RequestMapping(value = "/getUserById")
    public User getUserById(Integer id){
        if(id!=null){
            return userService.selectById(id);
        }
        return null;
    }

    @RequestMapping(value = "/findUserByUsername")
    public User findUserByUsername(String username){
        return userService.getUserByUsername(username);
    }

    @RequestMapping(value = "/test")
    public ResultEntity test(@CookieValue(name = "username",required = false) String name, HttpServletResponse resp){
//        int i=10/0; //模拟系统异常

        if(name.equals("Amos")){ //模拟业务异常
            throw new BusinessException("0001","xxxx错误");
        }
        System.out.println("username = " + name );
        Cookie cookie=new Cookie("username","Amos");
        cookie.setMaxAge(10000);
        cookie.setPath("/");
        resp.addCookie(cookie);
        return ResultEntity.success();
    }
}
