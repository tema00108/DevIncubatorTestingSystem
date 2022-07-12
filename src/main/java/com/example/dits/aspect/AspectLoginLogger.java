package com.example.dits.aspect;

import com.example.dits.dto.UserInfoDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Component
@Aspect
public class AspectLoginLogger {
    private final AuthenticationLogger authenticationLogger;

    @Autowired
    public AspectLoginLogger(AuthenticationLogger authenticationLogger) {
        this.authenticationLogger = authenticationLogger;
    }

    @Pointcut("execution(* com.example.dits.controllers.SecurityController.loginHandle(..))")
    private void logAuthentication() {

    }

    @Around("logAuthentication() && args(session)")
    private Object aroundAuthentication(ProceedingJoinPoint proceedingJoinPoint, Object session) throws Throwable {
        Object retVal = proceedingJoinPoint.proceed();
        HttpSession resultSession = (HttpSession) session;
        UserInfoDTO user = (UserInfoDTO) resultSession.getAttribute("user");
        authenticationLogger.log(new Date(), user);

        return retVal;
    }
}
