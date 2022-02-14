package com.orion.cafeorion.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @author Dmitriy
 * @since 10.02.2022
 */
@Aspect
@Component
public class LoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(* com.orion.cafeorion.controller..*(..))")
    public Object aroundAllControllersMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        logger.info(String.format("%s. Entered {%s.%s}, with arguments: {%s}",
                authentication.getPrincipal(),
                proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName(),
                Arrays.toString(proceedingJoinPoint.getArgs())));

        Object targetMethodResult;

        try {
            targetMethodResult = proceedingJoinPoint.proceed();
        } catch (Exception e) {
            logger.error(String.format("Exception in {%s.%s} with cause: {%s}.",
                    proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                    proceedingJoinPoint.getSignature().getName(),
                    e.getCause() == null ? e.toString() : e.getCause()));
            throw e;
        }

        logger.info(String.format("Method {%s.%s} completed successfully.",
                proceedingJoinPoint.getSignature().getDeclaringTypeName(),
                proceedingJoinPoint.getSignature().getName()
                ));

        return targetMethodResult;
    }


}
