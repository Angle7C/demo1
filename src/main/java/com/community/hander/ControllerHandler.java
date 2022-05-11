package com.community.hander;

//import org.springframework.web.bind.annotation.RestController;
import com.community.enums.ErrorEnum;
import com.community.execption.ControllerExecption;
import com.community.execption.MyExecption;
import com.community.execption.ServiceExecption;
import com.community.model.ResultJson;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.community.execption.MyExecption;


@Slf4j
@RestControllerAdvice
public class ControllerHandler{
    private static final Logger logger= LoggerFactory.getLogger(ControllerHandler.class);
    @ExceptionHandler(value = Exception.class)
    public ResultJson controllerHanler(Exception e){
        logger.error("【系统异常】: {}",e.getMessage());
        e.printStackTrace();
        return new ResultJson(ErrorEnum.SYSTEM_EXECPTION);
    }
    @ExceptionHandler(value = MyExecption.class)
    public ResultJson controllerHanler(MyExecption e){
        logger.error("【未知业务异常】: {}",e.getMessage());
        e.printStackTrace();
        return new ResultJson(ErrorEnum.SYSTEM_EXECPTION.getCode(), ErrorEnum.SYSTEM_EXECPTION.getMessage());
    }
    @ExceptionHandler(value = ControllerExecption.class)
    public ResultJson controllerHanler(ControllerExecption e){
        logger.error("【业务异常】: {}",e.getMessage());
        e.printStackTrace();
        return new ResultJson(e.getCode(),e.getMessage());
    }
    @ExceptionHandler(value = BindException.class)
    public ResultJson bindHandler(BindException e){
        logger.error("【校验异常】---检验字段: {}--约束条件：{}",e.getFieldError().getField(),e.getFieldError().getCodes());
        e.printStackTrace();
        return new ResultJson(ErrorEnum.CHECK_ERROE_FROM.getCode(), ErrorEnum.CHECK_ERROE_FROM.getMessage());
    }
    @ExceptionHandler(value = ServiceExecption.class)
    public ResultJson ServiceHander(ServiceExecption e){
        logger.error("【业务异常】: {}",e.getMessage());
        return new ResultJson(e.getCode(),e.getMessage());
    }
    @ExceptionHandler({IllegalStateException.class , IllegalArgumentException.class} )
    public ResultJson bindHandler(RuntimeException e){
        logger.error("【未通过校验】--- 条件: {}",e.getMessage());
        return new ResultJson(20002,e.getMessage());
    }
}
