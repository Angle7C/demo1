package com.community.execption;

import com.community.enums.ErrorEnum;

public class ServiceExecption extends MyExecption{

    public ServiceExecption(Integer code, String message) {
        super(code, message);
    }
    public ServiceExecption(ErrorEnum errorEnum) {
        super(errorEnum);
    }
}
