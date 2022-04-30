package com.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum ErrorEnum {
    USER_NOT_FOUND(20001,"账号或密码错误"),
    SYSTEM_EXECPTION(30002,"系统内部异常，请联系管理员"),
    CHECK_ERROE_FROM(20002,"校验错误"),
    CHECK_USER_LOGIN(20003,"未登录");
    private int Code;
    private String message;

}
