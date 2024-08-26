package com.scm.scm2_0.Helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

public class Helper {

    public static String getEmailofLoggedInUser(Authentication authentication){


        if(authentication instanceof OAuth2AuthenticationToken){

            String email = "";

            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

            DefaultOAuth2User oAuth2User = (DefaultOAuth2User) oAuth2AuthenticationToken.getPrincipal();
            
            if(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId().equalsIgnoreCase("google")){

                // System.out.println("google user" + oAuth2User.getName());

                email = oAuth2User.getAttribute("email");
            }

            else if(oAuth2AuthenticationToken.getAuthorizedClientRegistrationId().equalsIgnoreCase("github")){

                // System.out.println("github user" + oAuth2User.getName());

                email = oAuth2User.getAttribute("email") == null ? oAuth2User.getAttribute("login")+"@gmail.com" : oAuth2User.getAttribute("email").toString();

            }

            return email;
        }

        else{

            // System.out.println("our databse user" + authentication.getName());
            return authentication.getName();
        }

        
    }

}
