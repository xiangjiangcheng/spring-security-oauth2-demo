package com.example.security.config.security.resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器
 * @author Xiang Jiangcheng
 */
@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // resources.authenticationEntryPoint(new AuthenticationExceptionEntryPoint())
        //         .accessDeniedHandler(new CustomAccessDeniedHandler());
       // resources.tokenExtractor()
    }

    // todo: should be enabled access control
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers().anyRequest()
                // there is permitted all access we are use annotation(@PreAuthorize("hasRole('MERCHANT')")) to control the access
                .and().authorizeRequests().anyRequest().permitAll();
    }
}
