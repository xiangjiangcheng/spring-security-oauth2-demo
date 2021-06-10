package com.example.security.service;

import com.example.security.dto.Response;
import com.example.security.dto.auth.LoginRequest;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

/**
 * @author Xiang JiangCheng
 */
public interface AuthService {

    Response<OAuth2AccessToken> doLogin(LoginRequest request);
}
