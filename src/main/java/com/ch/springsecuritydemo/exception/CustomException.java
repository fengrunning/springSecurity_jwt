package com.ch.springsecuritydemo.exception;

import lombok.Getter;


@Getter
public class CustomException extends BaseException {

    public CustomException(){

    };

    public CustomException(Integer code ,String msg) {
        super(code,msg);
    }

}
