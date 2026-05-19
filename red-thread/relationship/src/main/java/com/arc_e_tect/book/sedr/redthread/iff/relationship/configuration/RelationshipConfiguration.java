package com.arc_e_tect.book.sedr.redthread.iff.relationship.configuration;

import com.arc_e_tect.book.sedr.redthread.iff.relationship.application.domain.service.RetrieveApplicationVersionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RelationshipConfiguration {

    @Bean
    public RetrieveApplicationVersionService retrieveApplicationVersionService() {
        return new RetrieveApplicationVersionService();
    }
}
