package com.example.security.service.impl;

import com.example.security.config.security.auth.MyUserDetails;
import com.example.security.domain.user.User;
import com.example.security.repository.UserRepository;
import com.example.security.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Xiang JiangCheng
 */
@Service
public class MyUserDetailsServiceImpl implements MyUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User [" + username + "] not found.");
        }
        return MyUserDetails.of(user);
    }
}
