package com.scm.scm2_0.Config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.scm2_0.Entities.User;
import com.scm.scm2_0.Enums.Providers;
import com.scm.scm2_0.Helper.AppConstants;
import com.scm.scm2_0.Repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler{
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
        
            logger.info("OAuthAuthenticationSuccessHandler");

            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            
            String authorizedClientRegistrationID = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            logger.info(authorizedClientRegistrationID);

            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) oAuth2AuthenticationToken.getPrincipal();

            oAuth2User.getAttributes().forEach((key,value)-> {

                logger.info(key + ":" + value);
            });

            oAuth2User.getAuthorities().forEach(i->{

                System.out.println(i);
            });

            User user = new User();

            // Setting default user attributes which is indpendent of provider

            user.setUserId(UUID.randomUUID().toString());
            user.setRoleList(List.of(AppConstants.ROLE_USER));
            user.setEmailVerified(true);
            user.setEnabled(false);
            user.setPassword(passwordEncoder.encode("abc"));
            user.setEnabled(true);

            if(authorizedClientRegistrationID.equalsIgnoreCase("google")){

                user.setEmail(oAuth2User.getAttribute("email").toString());
                user.setProfilePic(oAuth2User.getAttribute("picture").toString());
                user.setName(oAuth2User.getAttribute("name").toString());
                user.setProviderUserId(oAuth2User.getName());
                user.setProvider(Providers.GOOGLE);
                user.setAbout("This account is created using google..");
            }

            else if(authorizedClientRegistrationID.equalsIgnoreCase("github")){

                String email = oAuth2User.getAttribute("email");
                String picture = oAuth2User.getAttribute("avatar_url").toString();
                String name = oAuth2User.getAttribute("name").toString();
                String providerUserId = oAuth2User.getName();

                user.setEmail(email != null ? email.toString() : oAuth2User.getAttribute("login").toString()+ "@gmail.com");
                user.setProfilePic(picture);
                user.setName(name);
                user.setProviderUserId(providerUserId);
                user.setProvider(Providers.GITHUB);
                user.setAbout("This account is created using github..");
            }

            else{

                logger.info("OAuthAuthenticationSuccessHandler: Unknown Provider");
            }

            // save the user

            User user1 = userRepo.findByEmail(user.getEmail()).orElse(null);

            User savedUser = user1==null ? userRepo.save(user) : user;

            logger.info("{} -> {}",savedUser);


            // DefaultOAuth2User user= (DefaultOAuth2User) authentication.getPrincipal();

            // // System.out.println(authentication.getPrincipal());
            // logger.info(user.getName());

            // user.getAttributes().forEach((key,value)->{

            //     logger.info("{} => {}",key,value);
            // });

            // logger.info(user.getAuthorities().toString());

            // // save user info getting from google to our own database

            // String name = user.getAttribute("name").toString();
            // String email = user.getAttribute("email").toString();
            // String picture = user.getAttribute("picture").toString();

            // User user1 = new User();

            // user1.setEmail(email);
            // user1.setName(name);
            // user1.setProfilePic(picture);
            // user1.setPassword(passwordEncoder.encode("abc"));
            // user1.setUserId(UUID.randomUUID().toString());
            // user1.setProvider(Providers.GOOGLE);
            // user1.setEnabled(true);

            // user1.setEmailVerified(true);
            // user1.setProviderUserId(user.getName());
            // user1.setRoleList(List.of(AppConstants.ROLE_USER));
            // user1.setAbout("This account is created using google..");

            // User user2 = userRepo.findByEmail(email).orElse(null);

            // if(user2 == null){

            //     userRepo.save(user1);
            //     logger.info("User saved:",email);
            // }

            // // response.sendRedirect("/home");

            new DefaultRedirectStrategy().sendRedirect(request,response,"/user/dashboard");
        }

}
