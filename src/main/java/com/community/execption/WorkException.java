package com.community.execption;

import com.community.enums.ErrorEnum;
import lombok.Getter;

public class WorkException extends RuntimeException {
    @Getter
    private Integer code;
    public WorkException(String message,Integer code){
        super(message);
        this.code=code;
    }
    public WorkException(ErrorEnum errorEnum){
        this(errorEnum.getMessage(), errorEnum.getCode());
    }
}
