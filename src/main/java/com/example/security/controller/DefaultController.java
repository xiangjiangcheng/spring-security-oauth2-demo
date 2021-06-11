package com.example.security.controller;

import com.example.security.config.security.auth.MyUserDetails;
import com.example.security.dto.Response;
import com.example.security.support.SecuritySupport;
import com.example.security.utils.GsonUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xiang JiangCheng
 */
@Api(tags = "测试")
@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/default")
public class DefaultController {

    private static final Logger logger = LogManager.getLogger(DefaultController.class);

    private final SecuritySupport securitySupport;

    public DefaultController(SecuritySupport securitySupport) {
        this.securitySupport = securitySupport;
    }

    @ApiOperation("获取登录用户信息")
    @GetMapping("/load-user")
    public Response<MyUserDetails> loadUser() {
        MyUserDetails userDetails = securitySupport.currentUser();
        logger.info("default controller overview... {}", GsonUtils.toJson(userDetails));
        return Response.success(userDetails);
    }

}
