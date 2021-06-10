package com.example.security.controller;

import com.example.security.dto.Response;
import com.example.security.dto.auth.LoginRequest;
import com.example.security.service.AuthService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Xiang JiangCheng
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Response<OAuth2AccessToken> login(@RequestBody LoginRequest request) {
        return authService.doLogin(request);
    }


}
