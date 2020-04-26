package cm.services;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ProcessRuntimeAspect {
    @Pointcut("execution(* cm.services..*.*(..))")
    public  void pointCut(){}
    @Around("pointCut()")
    public Object doArround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        long startDate = System.currentTimeMillis();
       Object obj= proceedingJoinPoint.proceed();
        long endDate = System.currentTimeMillis();
        System.out.println(proceedingJoinPoint.getTarget()+","+proceedingJoinPoint.getSignature().getName()+"程序运行时间"+(endDate-startDate)+"ms");
        return obj;
    }

}
