
package com.springmvc.aop;

import com.springmvc.dao.UserDao;
import com.springmvc.dao.UserLoginAuditDAO;
import com.springmvc.entity.ExecutionAuditCheck;
import com.springmvc.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import com.springmvc.dao.ExecutionAuditCheckDao;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Autowired
    private ExecutionAuditCheckDao executionAuditCheckDao;

    @Autowired
    private UserLoginAuditDAO userLoginAuditDAO;

    @Autowired
    private UserDao userDao;

    // point cut for all public methods in controllers
    @Pointcut("execution(public * com.springmvc.controller.*.*(..))")
    private void publicControllerMethods () {
    }

    @Pointcut("execution(public * com.springmvc.security.CustomAuthenticationSuccessHandler.onAuthenticationSuccess(..))")
    private void auditLogin() {
    }


    @Around("publicControllerMethods()")
    public Object logAround (ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis ();
        Object[] args = joinPoint.getArgs ();
        String methodName = joinPoint.getSignature ().getName ();
        log.info ("Calling method: {}({})", methodName, Arrays.toString (args));
        Object result = joinPoint.proceed ();
        log.info ("Result from method {}() - {}", methodName, result);
        long responseTime = System.currentTimeMillis () - start;
        log.info ("Method name {}, output {}, Execution time {} ms", methodName, result, responseTime);

        ExecutionAuditCheck audit = new ExecutionAuditCheck ();
        audit.setNameOfController (joinPoint.getTarget ().getClass ().getSimpleName ());
        audit.setNameOfMethod (methodName);
        audit.setExecutionTime (responseTime);

        String message = executionAuditCheckDao.saveExecutionAuditCheck (audit);
        log.info ("Audit record {}", message);
        return result;
    }


    @After("auditLogin()")
    public void beforeLogin (JoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis ();
        Object[] args = joinPoint.getArgs ();
        if (args != null && args.length > 0) {
            UserModel userData = (UserModel) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            javax.servlet.http.HttpServletRequest httpServletRequest = (javax.servlet.http.HttpServletRequest) args[0];
            String inputRole = httpServletRequest.getParameter ("roleName");
            log.info ("Creating audit record for user {} with role {}", userData, inputRole);
            if (userData != null) {
                if (userData.getRoles ().stream ().anyMatch (role -> role.getRoleName ().equals (inputRole))) {
                    log.info ("Saving audit record for user {} login at {} with role {}", userData.getUsername (), start, inputRole);
                    userLoginAuditDAO.save(userData, inputRole, start);
                }
            }
        }
    }


}

