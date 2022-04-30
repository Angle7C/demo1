package com.community.execption;

import com.community.enums.ErrorEnum;
import lombok.Getter;

public class ControllerExecption extends WorkException {
    public ControllerExecption(ErrorEnum errorEnum){
        super(errorEnum.getMessage(),errorEnum.getCode());
    }
}
