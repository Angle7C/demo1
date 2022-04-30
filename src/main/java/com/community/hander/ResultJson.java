package com.community.hander;

import com.community.enums.ErrorEnum;
import com.community.enums.SucessEnum;
import com.community.execption.ControllerExecption;
import lombok.*;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultJson<T> {
    private Integer code;
    private String message;
    private T  data;
    private List<T> datas;
    public ResultJson(String message, T date){
        this(0,message,date,null);
    }
    public ResultJson(String message, List<T> datas){
        this(0,message,null,datas);
    }
    public ResultJson(String message){
        this(0,message,null,null);
    }
    public ResultJson(int code, String message){
        this(code,message,null,null);
    }
    public void failed(ErrorEnum errorEnum){
        this.code=errorEnum.getCode();
        this.message=errorEnum.getMessage();
    }
    public ResultJson(SucessEnum sucessEnum){
        this(sucessEnum.getSucessCode(),sucessEnum.getMessage(),null,null);
    }
    public ResultJson(ControllerExecption e){
        this(e.getCode(),e.getMessage());
    }
}
