package com.apress.spring6recipes.post.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.apress.spring6recipes.post.BackOffice;
import com.apress.spring6recipes.post.BackOfficeImpl;

/**
 * Created by marten on 02-06-14.
 */
@Configuration
public class BackOfficeConfiguration {

    @Bean
    public BackOffice backOffice() {
        return new BackOfficeImpl();
    }

}
