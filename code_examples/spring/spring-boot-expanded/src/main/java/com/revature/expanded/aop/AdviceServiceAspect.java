package com.revature.expanded.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/*
 * This class showcases advice types.
 *
 * Typically, you would not apply all five advice types to the same
 * method, but we are doing so here to illustrate how each type
 * behaves, side-by-side
 *
 * Note: In this example, we are referencing the same method
 *  on the 'AdviceService' class multiple times, duplicating effort
 *  This is intentional - see the 'AdviceServiceAspectPointcut'
 *  class for details
 */
@Aspect
@Component
public class AdviceServiceAspect {

    @Before("execution(* com.revature.expanded.aop.AdviceService.processData(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        System.out.println("[@Before] - About to process data with these args: "
                + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "execution(* com.revature.expanded.aop.AdviceService.processData(..))",
            returning = "result")
    public void afterReturningAdvice(Object result) {
        System.out.println("[@AfterReturning] - Data Processed successfully: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.revature.expanded.aop.AdviceService.processData(..))",
            throwing = "ex")
    public void afterThrowingAdvice(Exception ex) {
        System.out.println("[@AfterThrowing] - Data Processing failed: " + ex.getMessage());
    }

    @After("execution(* com.revature.expanded.aop.AdviceService.processData(..))")
    public void afterAdvice() {
        System.out.println("[@After] - Data Processing Attempt Finished | (Success or Failure)");
    }

    @Around("execution(* com.revature.expanded.aop.AdviceService.processData(..))")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("[@Around] Before Processing");
        Object result = pjp.proceed();  // proceed() invokes the actual target method
                                        // since this marks the invocation, we can
                                        // change the data returned
        System.out.println("[@Around] After Processing");
        return result + " - Altered by @Around";
    }
}
