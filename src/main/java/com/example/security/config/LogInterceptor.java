package com.example.security.config;

import com.example.security.utils.GsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Xiang JiangCheng
 */
public class LogInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LogManager.getLogger(LogInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        request.setAttribute("startTime", System.currentTimeMillis());
        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        Long startTimestamp = (Long) request.getAttribute("startTime");
        long endTimestamp = System.currentTimeMillis();
        String method = request.getMethod();
        int status = response.getStatus();
        StringBuffer requestURL = request.getRequestURL();
        if (status != 200) {
            logger.warn("[{}][{}] took {} ms {}", method, status, (endTimestamp - startTimestamp), requestURL);
        } else {
            logger.info("[{}][{}] took {} ms {}", method, status, (endTimestamp - startTimestamp), requestURL);
        }
        super.afterCompletion(request, response, handler, ex);
    }
}
