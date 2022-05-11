package com.community.enums;

import lombok.Getter;
@Getter
public enum SucessEnum {
    FILE_UPLOAD("文件上传成功"),
    USER_LOGIN( "登录成功"),
    CREATE_QUESTION("创建问题成功"),
    GITEE_LOGIN("GITEE第三方登录成功"),
    GITHUB_LOGIN("GITHUB第三方登录成功"),
    USER_LOHOUT("登出成功"),
    QUESTION_SEARCH("问题查询成功" ),
    COMMENT_IN_QUESTION("查找该问题下的评论成功" ),
    PUSH_COMMENT("发送评论成功" ),
    ADD_LIKE("添加喜欢成功" ),
    DEL_COMMENT("删除成功" );
    private String message;
    SucessEnum(String message){
        this.message=message;

    }
}
