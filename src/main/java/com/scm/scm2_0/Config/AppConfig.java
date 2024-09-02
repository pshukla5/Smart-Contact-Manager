package com.scm.scm2_0.Config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Configuration
public class AppConfig {

    @Autowired
    private Environment environment;

    @Value("${cloudinary.cloud_name}")
    private String cloudinaryCloudName;

    @Value("${cloudinary.api_key}")
    private String cloudinaryAPIKey;

    @Value("${cloudinary.api_secret}")
    private String cloudinaryAPISecret;


    @Bean
    public Cloudinary cloudinary(){

        return new Cloudinary(

            ObjectUtils.asMap(

                "cloud_name", environment.getProperty("cloudinary.cloud_name"), // from environment variables using application.properties
                "api_key",environment.getProperty("Cloudinary_APIKey"), // from system environment variables using our machine system variables
                "api_secret",cloudinaryAPISecret // from this class itself
            )
        );
    }
    
}
