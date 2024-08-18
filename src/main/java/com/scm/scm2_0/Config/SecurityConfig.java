package com.scm.scm2_0.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.scm.scm2_0.Services.Implementations.SecurityCustomUserDetailsService;

@Configuration
public class SecurityConfig{

    // @Bean
    // // User create and login using java code with in memory service
    // public UserDetailsService userDetailsService(){

    //     UserDetails user1 = User.withDefaultPasswordEncoder()
    //                         .username("prabhat")
    //                         .password("prabhat")
    //                         .roles("ADMIN")
    //                         .build();
        
    //     UserDetails user2 = User.withUsername("prashant")
    //                         .password("prashant")
    //                         .roles("ADMIN")
    //                         .build();

    //     return new InMemoryUserDetailsManager(user1,user2);
    // }

    @Autowired
    private SecurityCustomUserDetailsService securityCustomUserDetailsService;

    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // UserDetailsService ka object
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailsService);
        
        // Password encoder ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }
}
