package com.community.hander;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
@Slf4j
public class ControllerLog {
//    private Logger log= LoggerFactory.getLogger(ControllerLog.class);

    @Pointcut("execution( public * com.community.controller.*.*(..) )")
    public void point() {}
    @Before("point()")
    public void logData(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes requestAttributes =(ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        log.info("Name {} port {} method {}",joinPoint.getSignature().getName(),requestAttributes.getRequest().getServerPort(),requestAttributes.getRequest().getMethod());
    }
}
