package com.example.security.config.security.resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Xiang JiangCheng
 */
@Component
public class ResourceDetailsBuilder {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientSecret;

    @Value("${security.oauth2.client.grant-type}")
    private String grantType;

    @Value("${security.oauth2.client.access-token-uri}")
    private String accessTokenUri;

    public CustomerResourceDetails build(String username, String password) {
        CustomerResourceDetails resourceDetails = new CustomerResourceDetails();
        resourceDetails.setUsername(username);
        resourceDetails.setPassword(password);
        resourceDetails.setClientId(clientId);
        resourceDetails.setClientSecret(clientSecret);
        resourceDetails.setAccessTokenUri(accessTokenUri);
        resourceDetails.setGrantType(grantType);
        return resourceDetails;
    }

}
