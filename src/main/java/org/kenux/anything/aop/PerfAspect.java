package org.kenux.anything.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class PerfAspect {

    @Around("execution(* org.kenux.anything..*.EventService.*(..))")
    public Object logPref(ProceedingJoinPoint joinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object retVal = joinPoint.proceed(); // 메서드 호출 자체를 감쌈
        log.info("소요 시간 = " + (System.currentTimeMillis() - begin));
        return retVal;
    }
}