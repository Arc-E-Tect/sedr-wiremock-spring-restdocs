package com.arc_e_tect.book.sedr.redthread.iff.relationship.configuration;

import com.arc_e_tect.book.sedr.redthread.iff.relationship.application.domain.service.RetrieveApplicationVersionService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RelationshipConfigurationTest {

    private final RelationshipConfiguration configuration = new RelationshipConfiguration();

    @Test
    void retrieveApplicationVersionService_returnsServiceInstance() {
        var service = configuration.retrieveApplicationVersionService();

        assertThat(service).isNotNull().isInstanceOf(RetrieveApplicationVersionService.class);
    }
}
