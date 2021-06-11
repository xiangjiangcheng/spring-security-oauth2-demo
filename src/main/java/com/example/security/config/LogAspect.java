package com.example.security.config;

import com.example.security.utils.GsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Xiang JiangCheng
 */
@Component
@Aspect
public class LogAspect {

    private final static Logger logger = LogManager.getLogger(LogAspect.class);

    /**
     * 以 controller 包下定义的所有请求为切入点
     */
    @Pointcut("execution(public * com.example.security.controller..*.*(..))")
    public void webLog() {
    }

    /**
     * 环绕
     *
     * @param proceedingJoinPoint
     * @return
     * @throws Throwable
     */
    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String requestParams = GsonUtils.toJson(proceedingJoinPoint.getArgs());

        Object result = proceedingJoinPoint.proceed();

        HttpServletResponse httpServletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        assert httpServletResponse != null;
        int status = httpServletResponse.getStatus();
        logger.info("[{}][{}] took {} ms {} params {}", method, status, System.currentTimeMillis() - startTime, url, requestParams);
        return result;
    }

}
