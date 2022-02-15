package com.zawthan.pma.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class ApplicationLoggerAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(com.zawthan.pma.controllers..*)")
    public void definePackagePointcuts(){
        // empty method just to name the location for pointcut
    }

    @Around("definePackagePointcuts()")
    public Object logAround(ProceedingJoinPoint jp) {
        log.debug("\n \n \n");
        log.debug("\n \n ************** Before Method Execution ***************** \n {}.{} () with arguments[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
        log.debug(" \n ______________________________________________ \n \n \n");

        Object o = null;

        try {
            o = jp.proceed();
        }
        catch (Throwable e) {
            e.printStackTrace();   // Auto generate catch block
        }

        log.debug("\n \n ************** After Method Execution ***************** \n {}.{} () with arguments[s] = {}",
                jp.getSignature().getDeclaringTypeName(),
                jp.getSignature().getName(), Arrays.toString(jp.getArgs()));
        log.debug(" \n ______________________________________________ \n \n \n");

        return o;

    }
}
