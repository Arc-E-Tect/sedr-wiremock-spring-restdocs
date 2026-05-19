package com.arc_e_tect.book.sedr.redthread.iff.useraccount.configuration;

import com.arc_e_tect.book.sedr.redthread.iff.useraccount.application.domain.service.RetrieveApplicationVersionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserAccountConfiguration {

    @Bean
    public RetrieveApplicationVersionService retrieveApplicationVersionService() {
        return new RetrieveApplicationVersionService();
    }
}
