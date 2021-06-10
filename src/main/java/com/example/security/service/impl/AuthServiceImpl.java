package com.example.security.service.impl;

import com.example.security.config.security.resource.AccessTokenProvider;
import com.example.security.config.security.resource.ResourceDetailsBuilder;
import com.example.security.dto.Response;
import com.example.security.dto.auth.LoginRequest;
import com.example.security.repository.UserRepository;
import com.example.security.service.AuthService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

/**
 * @author Xiang JiangCheng
 */
@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LogManager.getLogger(AuthServiceImpl.class);

    private final AccessTokenProvider accessTokenProvider;
    private final ResourceDetailsBuilder resourceDetailsBuilder;
    private final UserRepository userRepository;

    public AuthServiceImpl(AccessTokenProvider accessTokenProvider, ResourceDetailsBuilder resourceDetailsBuilder, UserRepository userRepository) {
        this.accessTokenProvider = accessTokenProvider;
        this.resourceDetailsBuilder = resourceDetailsBuilder;
        this.userRepository = userRepository;
    }

    @Override
    public Response<OAuth2AccessToken> doLogin(LoginRequest request) {
        try {
            ResourceOwnerPasswordResourceDetails details = resourceDetailsBuilder.build(request.getUsername(), request.getPassword());
            OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(details);
            restTemplate.setAccessTokenProvider(accessTokenProvider);
            OAuth2AccessToken accessToken = restTemplate.getAccessToken();
            return Response.success(accessToken);
        } catch (Exception e) {
            logger.error("Exception happened when authentication", e);
            return Response.failed(70000, "Login failed.");
        }
    }
}
