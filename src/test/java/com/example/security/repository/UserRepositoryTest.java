package com.example.security.repository;

import com.example.security.Application;
import com.example.security.domain.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Xiang JiangCheng
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class})
class UserRepositoryTest {

    private Logger logger = LogManager.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    @Test
    void findByUsername() {
        String username = "17783715544";
        User user = userRepository.findByUsername(username);
        if (user == null) {
            logger.info("User not found.");
        }
    }
}