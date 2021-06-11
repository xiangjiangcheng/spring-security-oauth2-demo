package com.example.security.utils;

import com.example.security.config.security.auth.MyUserDetails;
import com.example.security.domain.user.User;
import com.example.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Map;

/**
 * @author Xiang JiangCheng
 */
@Component
public class TokenUtils {

    private final Logger logger = LoggerFactory.getLogger(TokenUtils.class);

    private final UserRepository userRepository;
    private final JdbcTokenStore accessTokenStore;

    public TokenUtils(UserRepository userRepository, JdbcTokenStore accessTokenStore) {
        this.userRepository = userRepository;
        this.accessTokenStore = accessTokenStore;
    }

    /**
     * 通过用户guid删除token
     */
    public void removeTokenByUserGuid(String userGuid) {

        User account = userRepository.findByGuid(userGuid);
        if (account == null) {
            return;
        }

        // remove accessToken and refreshToken
        Collection<OAuth2AccessToken> oAuth2AccessTokens = accessTokenStore.findTokensByUserName(account.getUsername());
        for (OAuth2AccessToken accessToken : oAuth2AccessTokens) {
            if (accessToken.getRefreshToken() != null) {
                accessTokenStore.removeRefreshToken(accessToken.getRefreshToken());
            }
            accessTokenStore.removeAccessToken(accessToken);
        }
    }

    public void updateName(String userName, String name) {
        logger.info("Update {} name to...{}", userName, name);
        Collection<OAuth2AccessToken> oAuth2AccessTokens = accessTokenStore.findTokensByUserName(userName);
        for (OAuth2AccessToken accessToken : oAuth2AccessTokens) {
            Map<String, Object> additionalInformation = accessToken.getAdditionalInformation();
            if (!org.springframework.util.CollectionUtils.isEmpty(additionalInformation)) {
                additionalInformation.put("name", name);
            }
            OAuth2Authentication oAuth2Authentication = accessTokenStore.readAuthentication(accessToken);
            Object principal = oAuth2Authentication.getPrincipal();
            if (principal instanceof MyUserDetails) {
                MyUserDetails userDetails = (MyUserDetails) principal;
                userDetails.updateName(name);
            }
            accessTokenStore.storeAccessToken(accessToken, oAuth2Authentication);
        }
    }

}
