package com.example.security.config.exception;

import com.example.security.dto.Response;
import com.example.security.utils.GsonUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理类
 *
 * @author Xiang JiangCheng
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    /**
     * 运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public Response<Void> runtimeExceptionHandler(Exception e) {
        logger.error("Runtime exception ===>", e);
        return Response.failed(500, "系统内部错误");
    }

    /**
     * 自定义业务异常
     */
    @ExceptionHandler(BizException.class)
    @ResponseBody
    public Response<Void> bizExceptionHandler(BizException e) {
        logger.error("Business exception ===> {}", e.errorMsg);
        logger.error(e);
        return Response.failed(e.errorCode, e.errorMsg);
    }

    /**
     * OAuth2Exception
     */
    @ExceptionHandler(OAuth2Exception.class)
    public void oAuth2Exception(OAuth2Exception e, HttpServletResponse response) {
        logger.error(e.getMessage());
        try {
            response.setStatus(401);
            response.setContentType("application/json");
            response.getWriter().write(GsonUtils.toJson(Response.failed(401, e.getMessage())));
        } catch (IOException ex) {
            logger.error(ex);
        }
    }

    /**
     * AccessDeniedException
     */
    @ExceptionHandler(AccessDeniedException.class)
    public void accessDeniedException(AccessDeniedException e, HttpServletResponse response) {
        logger.error(e.getMessage());
        try {
            response.setStatus(403);
            response.setContentType("application/json");
            response.getWriter().write(GsonUtils.toJson(Response.failed(403, e.getMessage())));
        } catch (IOException ex) {
            logger.error(ex);
        }
    }


}
