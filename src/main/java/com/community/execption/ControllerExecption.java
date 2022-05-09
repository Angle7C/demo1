package com.community.execption;

import com.community.enums.ErrorEnum;
import lombok.Getter;

public class ControllerExecption extends MyExecption {
    public ControllerExecption(ErrorEnum errorEnum){
        super(errorEnum);
    }
    public ControllerExecption(Integer code,String message){
        super(code,message);
    }
}
