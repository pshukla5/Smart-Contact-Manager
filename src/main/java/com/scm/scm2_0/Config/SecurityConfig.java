package com.scm.scm2_0.Config;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer.AuthorizationManagerRequestMatcherRegistry;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.security.config.Customizer;

import com.scm.scm2_0.Services.Implementations.SecurityCustomUserDetailsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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

        // return new InMemoryUserDetailsManager(user1,user2);
    // }

    // Configuration of Authentication provider

    @Autowired
    private SecurityCustomUserDetailsService securityCustomUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Autowired
    OAuthAuthenticationSuccessHandler oAuthAuthenticationSuccessHandler;

    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // UserDetailsService ka object
        daoAuthenticationProvider.setUserDetailsService(securityCustomUserDetailsService);
        
        // Password encoder ka object
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);

        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{


        // configuration
        // URLs ko configure kiya hai kon se public rhenge aur koun se private rhenge
        httpSecurity.authorizeHttpRequests(authorize -> {

            // authorize.requestMatchers("/home","/signup","/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();

        });

        // form default login
        // agar hme kuch change krna hoga to yha aayenge -> form login se related
        httpSecurity.formLogin(formLogin -> {

            formLogin.loginPage("/login");
            formLogin.loginProcessingUrl("/authenticate");
            // formLogin.successForwardUrl("/user/dashboard");
            formLogin.failureForwardUrl("/login?error=true");
            // formLogin.defaultSuccessUrl("/user/profile");
            formLogin.defaultSuccessUrl("/user/dashboard");
            formLogin.usernameParameter("email");
            formLogin.passwordParameter("password");
            // formLogin.failureHandler(null);
            // formLogin.successHandler(null);



        });

        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        httpSecurity.logout(logoutForm->{

            logoutForm.logoutRequestMatcher(AntPathRequestMatcher.antMatcher("/**/logout**"));
            logoutForm.logoutSuccessUrl("/login?logout=true");
        });


        httpSecurity.oauth2Login(oAuth ->{

            oAuth.loginPage("/login");
            oAuth.successHandler(oAuthAuthenticationSuccessHandler);
        });


        return httpSecurity.build();
    }
}
