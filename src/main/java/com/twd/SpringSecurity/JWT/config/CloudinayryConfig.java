package com.twd.SpringSecurity.JWT.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinayryConfig {

    @Value("${cloudinary.cloud-name}")
    private String cloudName;

    @Value("${cloudinary.api-secret}")
    private String apiSecret;

    @Value("${cloudinary.api-key}")
    private String apiKey;

    @Bean
    public Cloudinary cloudinary() {
        Map<String , String> config = new HashMap<>();
        config.put("cloud_name", cloudName);
        config.put("api_secret", apiSecret);
        config.put("api_key", apiKey);
        config.put("secure" , "true");
        return new Cloudinary(config);
    }

}
