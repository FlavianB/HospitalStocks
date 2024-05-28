package com.example.hospitalstocks.Configs;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class GeneralConfiguration {
    @Bean
    @Description("Spring Message Resolver")
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages"); // Assuming your message properties files are named messages.properties
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
}
