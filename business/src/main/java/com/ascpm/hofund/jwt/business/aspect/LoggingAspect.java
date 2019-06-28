package com.ascpm.hofund.jwt.business.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Around(value = "execution(* com.ascpm.hofund.jwt.business..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        log.info(MessageFormat.format(">> Start Time [{0}] <<", dateTimeFormatter.format(LocalDateTime.now())));
        String signature = this.getSignature(joinPoint);
        log.info(MessageFormat.format("==== signature [{0}] ====", signature));
        Object returnValue = joinPoint.proceed();
        log.info(MessageFormat.format("==== return [{0}] ====", Optional.ofNullable(returnValue).orElse("")));
        log.info(MessageFormat.format(">> End Time [{0}] <<", dateTimeFormatter.format(LocalDateTime.now())));
        return returnValue;
    }

    private String getSignature(ProceedingJoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String signatureName = joinPoint.getSignature().getName();
        StringBuilder builder = new StringBuilder();
        Optional.ofNullable(joinPoint.getArgs())
                .filter(it -> it.length > 0)
                .ifPresent(it -> Arrays.stream(it)
                        .forEach(arg -> {
                            if (arg instanceof String) {
                                builder.append(MessageFormat.format("\"{0}\",", arg));
                            } else {
                                builder.append(MessageFormat.format("{0},", arg));
                            }
                        }));
        return MessageFormat.format("{0}.{1}({2})", className, signatureName, Optional.of(builder.toString())
                .filter(it -> it.length() > 0)
                .map(it -> it.substring(0, it.length() - 1))
                .orElse(""));
    }
}
