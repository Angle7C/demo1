package com.community.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum SucessEnum {
    FILE_UPLOAD(10001,"文件上传成功"),
    USER_LOGIN( 10001,"登录成功"),
    GITEE_LOGIN(10001,"GITEE第三方登录成功"),
    GITHUB_LOGIN(10001,"GITHUB第三方登录成功"),
    USER_LOHOUT(10001,"登出成功");
    private int sucessCode;
    private String message;
    SucessEnum(int code,String message){
        this.sucessCode=code;
        this.message=message;

    }
}
