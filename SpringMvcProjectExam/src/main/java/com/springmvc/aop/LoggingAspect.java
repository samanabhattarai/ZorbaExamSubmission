
package com.springmvc.aop;

import com.springmvc.entity.ExecutionAuditCheck;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.springmvc.dao.ExecutionAuditCheckDao;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Autowired
   private ExecutionAuditCheckDao executionAuditCheckDao;

    // point cut for all public methods in controllers
    @Pointcut("execution(public * com.springmvc.controller.*.*(..))")
    private void publicControllerMethods() {
    }

    @Around("publicControllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object[] args = joinPoint.getArgs();
        String methodName = joinPoint.getSignature().getName();
        log.info("Calling method: {}({})", methodName,  Arrays.toString(args));
        Object result = joinPoint.proceed();
        log.info("Result from method {}() - {}", methodName, result);
        log.info("Method name {}, output {}, Execution time {} ms", methodName, result, System.currentTimeMillis() - start);

        ExecutionAuditCheck auditCheck = new  ExecutionAuditCheck();

        ExecutionAuditCheck audit = new ExecutionAuditCheck();
        audit.setNameOfController(joinPoint.getTarget().getClass().getSimpleName());
        audit.setNameOfMethod(joinPoint.getSignature().getName());
        audit.setExecutionTime (System.currentTimeMillis() - start);

        executionAuditCheckDao.saveExecutionAuditCheck (auditCheck);
        return result;
    }
}
