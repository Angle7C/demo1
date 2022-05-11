package com.community.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
public enum ErrorEnum {
    USER_NOT_FOUND(20001,"账号或密码错误"),
    SYSTEM_EXECPTION(40000,"系统内部异常，请联系管理员"),
    CHECK_ERROE_FROM(20002,"校验错误"),
    CHECK_USER_LOGIN(20003,"未登录"),
    SERVICE_EASY_ERROR(30001, "查询数据库异常"),
    SERVICE_HEAD_ERROR(30002, "查询对象异常"),
    SERVICE_NUM_ERROR(30002,"删除数据数量不符合" );;
    private int Code;
    private String message;

}
