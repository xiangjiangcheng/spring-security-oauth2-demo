package com.example.security.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xiang JiangCheng
 */
@PreAuthorize("isAuthenticated()")
@RestController
@RequestMapping("/default")
public class DefaultController {

    private static final Logger logger = LogManager.getLogger(DefaultController.class);

    @GetMapping("/overview")
    public void overview() {
        logger.info("default controller overview...");
    }

}
