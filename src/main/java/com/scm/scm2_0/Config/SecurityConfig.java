package com.scm.scm2_0.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    @Bean
    // User create and login using java code with in memory service
    public UserDetailsService userDetailsService(){

        UserDetails user1 = User.withDefaultPasswordEncoder()
                            .username("prabhat")
                            .password("prabhat")
                            .roles("ADMIN")
                            .build();
        
        UserDetails user2 = User.withUsername("prashant")
                            .password("prashant")
                            .roles("ADMIN")
                            .build();

        return new InMemoryUserDetailsManager(user1,user2);
    }
}
