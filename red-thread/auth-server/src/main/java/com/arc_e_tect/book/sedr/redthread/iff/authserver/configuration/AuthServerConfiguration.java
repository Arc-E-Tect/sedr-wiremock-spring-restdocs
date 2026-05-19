package com.arc_e_tect.book.sedr.redthread.iff.authserver.configuration;

import com.arc_e_tect.book.sedr.redthread.iff.authserver.application.domain.service.RetrieveApplicationVersionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthServerConfiguration {

    @Bean
    public RetrieveApplicationVersionService retrieveApplicationVersionService() {
        return new RetrieveApplicationVersionService();
    }
}
