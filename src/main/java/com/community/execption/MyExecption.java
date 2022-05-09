package com.community.execption;

import com.community.enums.ErrorEnum;
import lombok.Getter;

public class MyExecption extends RuntimeException{
    @Getter
    private Integer code;
    public MyExecption(Integer code,String message){
        super(message);
        this.code=code;
    }
    public MyExecption(ErrorEnum errorEnum){
        this(errorEnum.getCode(), errorEnum.getMessage());
    }

}
