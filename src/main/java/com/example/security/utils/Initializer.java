package com.example.security.utils;

import com.example.security.domain.user.OAuthClientDetails;
import com.example.security.domain.user.User;
import com.example.security.repository.OAuthClientDetailsRepository;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Xiang JiangCheng
 */
@Component
public class Initializer implements InitializingBean {

    @Value("${security.oauth2.client.client-id}")
    private String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private String clientPassword;

    private final OAuthClientDetailsRepository oAuthClientDetailsRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Initializer(OAuthClientDetailsRepository oAuthClientDetailsRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.oAuthClientDetailsRepository = oAuthClientDetailsRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        OAuthClientDetails oAuthClientDetails = oAuthClientDetailsRepository.findByClientId(clientId);
        if (oAuthClientDetails == null) {
            oAuthClientDetails = new OAuthClientDetails(clientId, passwordEncoder.encode(clientPassword), "all", null, "password,refresh_token", null, "all", null, 86400, 259200, null);
            oAuthClientDetailsRepository.save(oAuthClientDetails);
        }

        // 初始化用户
        User existedUser = userRepository.findByUsername("17783715544");
        if (existedUser == null) {
            User user = new User("大帅比", "17783715544", "17783715544", bCryptPasswordEncoder.encode("123456"), true, true, null);
            userRepository.save(user);
        }
    }
}
