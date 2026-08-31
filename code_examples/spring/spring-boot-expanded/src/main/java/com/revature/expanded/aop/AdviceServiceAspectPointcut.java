package com.revature.expanded.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/*
 * This class showcases the power of created a named pointcut
 * Compare this to the 'AdviceServiceAspect' class. Notice
 * that we can reference the named pointcut 'advisedMethod'
 * instead of including the entire Pointcut designator
 */
@Aspect
@Component
public class AdviceServiceAspectPointcut {

    /*
     * Here, we create a named pointcut, and then reference this
     * name in our Advice methods - this makes our code much more
     * readable and maintainable
    */
    @Pointcut("execution(* com.revature.expanded.aop.AdviceService.processData(..))")
    public void advisedMethod() {

    }

    @Before("advisedMethod()")
    public void beforeAdvice(JoinPoint joinPoint) {
        // Functionality...
    }

    @AfterReturning(pointcut = "advisedMethod()", returning = "result")
    public void afterReturningAdvice(Object result) {
        // Functionality...
    }

    @AfterThrowing(pointcut = "advisedMethod()", throwing = "ex")
    public void afterThrowingAdvice(Exception ex) {
        // Functionality...
    }

    @After("advisedMethod()")
    public void afterAdvice() {
        // Functionality...
    }
}
