package com.example.security.support;

import com.example.security.config.security.auth.MyUserDetails;
import com.example.security.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author Xiang Jiangcheng
 */
@Component
public class SecuritySupport {
    private final Logger logger = LoggerFactory.getLogger(SecuritySupport.class);

    private final UserRepository userRepository;

    public SecuritySupport(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public MyUserDetails currentUser() {
        MyUserDetails anonymousUser = MyUserDetails.anonymousUser();
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof MyUserDetails) {
                return (MyUserDetails) principal;
            }
            return anonymousUser;
        } catch (Exception e) {
            logger.warn("Get current user failed!", e);
            return anonymousUser;
        }
    }

    public String accountGuid() {
        MyUserDetails currentUser = this.currentUser();
        if (currentUser != null) {
            return currentUser.getGuid();
        }
        return null;
    }

    public void update(Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @Deprecated
    public void updateName(String name) {
        logger.info("Update name...{}", name);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MyUserDetails) {
            MyUserDetails userDetails = (MyUserDetails) principal;
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));
        }
    }
}
