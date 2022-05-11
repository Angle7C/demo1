package com.community.hander;

import com.community.enums.ErrorEnum;
import com.community.execption.ServiceExecption;
import com.community.utils.LogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.sound.sampled.DataLine;

@Aspect
@Component
public class ServicesLog {
    @Pointcut("execution(public int com.community.services.*.*(..))")
    public void pointLine(){}
    @Pointcut("execution(public Object com.community.services.*.*(..))")
    public void pointObject(){}
//    @AfterReturning(value="pointLine()", returning = "ans" )
    public void logInt(JoinPoint point, Integer ans){
        Signature signature = point.getSignature();
        if(ans==1){
            LogUtil.info("方法名称:{} 返回值: {}",signature.getName(),ans);
        }else if(ans==0){
            LogUtil.warn("方法名称:{} 返回值: {}",signature.getName(),ans);
        }else{
            LogUtil.error("方法名称:{} 返回值: {}",signature.getName(),ans);
            throw new ServiceExecption(ErrorEnum.SERVICE_EASY_ERROR);
        }
    }
//    @AfterReturning(value="pointObject()", returning = "ans" )
    public void logObject(JoinPoint point, Object ans){
        Signature signature = point.getSignature();
        if(ans!=null){
            LogUtil.info( "方法名称:{}  返回对象:{}",signature.getName(), ans);
        }else{
            LogUtil.error("方法名称:{} 返回对象:{}",signature.getName(), null);
            throw new ServiceExecption(ErrorEnum.SERVICE_HEAD_ERROR);
        }
    }
}
