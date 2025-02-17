package com.ch.springsecuritydemo.exception;


public class BaseException extends RuntimeException {

    private String msg;
    private Integer code;

    public BaseException() {
    }

    public BaseException(String msg) {
       this.msg=msg;
    }

    public BaseException(Integer code,String msg) {
        this.msg=msg;
        this.code=code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
