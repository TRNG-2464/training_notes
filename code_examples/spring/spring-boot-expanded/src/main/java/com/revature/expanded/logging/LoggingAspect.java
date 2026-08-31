package com.revature.expanded.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("@annotation(com.revature.expanded.logging.LogExecution)")
    public void loggedMethod () {}

    /*
     * The following Advice methods will execute Before and after
     * a method annotated with @LogExecution is invoked.
     *
     * getSignature() returns the method signature of the object
     *  (including the name, parameters, etc...)
     *
     * toShortString() gives the compact form of the signature
     *  i.e. it removes the fully qualified class name, return
     *  type and other details
     */

    // Logs information BEFORE the method is executed
    @Before("loggedMethod()")
    public void beforeAdvice(JoinPoint joinPoint) {

        String methodName = joinPoint.getSignature().toShortString();
        log.info("Invoking {} with args: {}", methodName, Arrays.toString(joinPoint.getArgs()));
    }

    // Logs information AFTER the method executes correctly
    @AfterReturning(pointcut = "loggedMethod()", returning = "result")
    public void afterReturningAdvice(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().toShortString();
        log.info("{} completed successfully, returned: {}", methodName, result);
    }

    // Logs information if the method THROWS an exception
    @AfterThrowing(pointcut="loggedMethod()", throwing = "ex")
    public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
        String methodName = joinPoint.getSignature().toShortString();
        log.warn("{} failed with {}: {}", methodName, ex.getClass().getSimpleName(), ex.getMessage());
    }
}
