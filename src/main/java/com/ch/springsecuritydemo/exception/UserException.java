package com.ch.springsecuritydemo.exception;



/**
 * user业务异常
 */
public class UserException extends  BaseException{

    public UserException() {
    }
    public UserException(Integer code ,String msg) {
        super(code,msg);
    }



}
