package com.example.security.config.security.resource;

import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordAccessTokenProvider;
import org.springframework.stereotype.Component;

/**
 * @author Xiang JiangCheng
 */
@Component
public class AccessTokenProvider extends ResourceOwnerPasswordAccessTokenProvider {
}
