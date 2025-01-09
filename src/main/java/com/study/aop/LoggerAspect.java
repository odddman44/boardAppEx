package com.study.aop;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.StringUtils;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LoggerAspect {

    @Around("execution(* com.study.*..*Controller.*(..)) || execution(* com.study.*..*Service.*(..)) || execution(* org.springframework.data.jpa.repository.JpaRepository+.*(..))")
    public Object printLog(ProceedingJoinPoint joinPoint) throws Throwable {

        String name = joinPoint.getSignature().getDeclaringTypeName();
        String type =
                StringUtils.contains(name, "Controller") ? "Controller ===> " :
                        StringUtils.contains(name, "Service") ? "Service ===> " :
                                StringUtils.contains(name, "Repository") ? "Repository ===> " :
                                        "";

        log.debug("[{}] {}.{}() with arguments: {}", type, name, joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        return joinPoint.proceed();
    }

}
