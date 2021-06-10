/*
 * Copyright (c) 2016 Tianbao Travel Ltd.
 * www.tianbaotravel.com
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Tianbao Travel Ltd. ("Confidential Information").
 * You shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement you
 * entered into with Tianbao Travel Ltd.
 */
package com.example.security.domain.user;

import com.example.security.domain.DomainObject;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author Daiyu Chen
 */
@Entity
@Table(name = "oauth_client_details")
public class OAuthClientDetails extends DomainObject {

    @Column(name = "client_id")
    private String clientId;

    @Column(name = "client_secret")
    private String clientSecret;

    /**
     * Separated by comma
     */
    @Column(name = "scope")
    private String scope;

    /**
     * Separated by comma
     */
    @Column(name = "resource_ids")
    private String resourceIds;

    /**
     * Separated by comma
     * authorization_code,client_credentials,password,refresh_token
     */
    @Column(name = "authorized_grant_types")
    private String authorizedGrantTypes;

    /**
     * Separated by comma
     */
    @Column(name = "web_server_redirect_uri")
    private String registeredRedirectUris;

    /**
     * Separated by comma
     */
    @Column(name = "autoapprove")
    private String autoApproveScopes;

    /**
     * Separated by comma
     */
    @Column(name = "authorities")
    private String authorities;

    @Column(name = "access_token_validity")
    private Integer accessTokenValiditySeconds;

    @Column(name = "refresh_token_validity")
    private Integer refreshTokenValiditySeconds;

    /**
     * Map<String, Object>
     */
    @Column(name = "additional_information")
    private String additionalInformation;

    public OAuthClientDetails() {
    }

    public OAuthClientDetails(String clientId, String clientSecret, String scope, String resourceIds, String authorizedGrantTypes, String registeredRedirectUris, String autoApproveScopes, String authorities, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds, String additionalInformation) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.resourceIds = resourceIds;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.registeredRedirectUris = registeredRedirectUris;
        this.autoApproveScopes = autoApproveScopes;
        this.authorities = authorities;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
        this.additionalInformation = additionalInformation;
    }

    public String getClientId() {
        return clientId;
    }

    public Set<String> getResourceIds() {
        if (StringUtils.hasText(resourceIds)) {
            return StringUtils.commaDelimitedListToSet(resourceIds);
        }
        return Collections.emptySet();
    }

    public boolean isSecretRequired() {
        return clientSecret != null;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public boolean isScoped() {
        return StringUtils.hasText(scope);
    }

    public Set<String> getScope() {
        return StringUtils.commaDelimitedListToSet(scope);
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public Set<String> getRegisteredRedirectUri() {
        return StringUtils.commaDelimitedListToSet(registeredRedirectUris);
    }

    public String getAuthorities() {
        return authorities;
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public boolean isAutoApprove(String scope) {
        if (StringUtils.isEmpty(autoApproveScopes)) {
            return false;
        }
        Set<String> autoApproveScopes = getAutoApproveScopes();
        for (String autoApproveScope : autoApproveScopes) {
            if (autoApproveScope.equals("true") || scope.matches(autoApproveScope)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Not implemented yet
     *
     * @return Empty
     */
    public Map<String, Object> getAdditionalInformation() {
        return Collections.emptyMap();
    }

    public Set<String> getAutoApproveScopes() {
        return StringUtils.commaDelimitedListToSet(autoApproveScopes);
    }

}
