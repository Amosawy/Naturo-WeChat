package com.awy.exception;

import com.awy.entity.base.BusinessException;
import com.awy.entity.base.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice //全局异常
public class GlobalExceptionHandler {

    /**
     * 处理系统异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResultEntity systemEx(Exception e){
        System.out.println("系统异常"+e.getMessage());
        return ResultEntity.error(e.getMessage());
    }

    /**
     * 处理业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public ResultEntity systemEx(BusinessException e){
        System.out.println("业务异常"+e.getMsg());
        return ResultEntity.error(e.getMsg());
    }
}
