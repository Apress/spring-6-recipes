package com.apress.spring6recipes.post.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.apress.spring6recipes.post.FrontDeskImpl;

@Configuration
@EnableScheduling
public class FrontOfficeConfiguration {

    @Bean
    public FrontDeskImpl frontDesk() {
			return new FrontDeskImpl();
    }

}
