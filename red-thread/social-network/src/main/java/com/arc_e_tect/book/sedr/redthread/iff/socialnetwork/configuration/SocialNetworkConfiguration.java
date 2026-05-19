package com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.configuration;

import com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.application.domain.service.RetrieveApplicationVersionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocialNetworkConfiguration {

    @Bean
    public RetrieveApplicationVersionService retrieveApplicationVersionService() {
        return new RetrieveApplicationVersionService();
    }
}
