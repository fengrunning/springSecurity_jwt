package com.ch.springsecuritydemo.handler;

import com.ch.springsecuritydemo.exception.BaseException;
import com.ch.springsecuritydemo.exception.UserException;
import com.ch.springsecuritydemo.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 用户业务错误
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(UserException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getCode(),ex.getMsg());
    }

}
