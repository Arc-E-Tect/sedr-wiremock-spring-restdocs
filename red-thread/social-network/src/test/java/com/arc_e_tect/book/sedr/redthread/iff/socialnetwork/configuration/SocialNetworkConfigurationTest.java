package com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.configuration;

import com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.application.domain.service.RetrieveApplicationVersionService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SocialNetworkConfigurationTest {

    private final SocialNetworkConfiguration configuration = new SocialNetworkConfiguration();

    @Test
    void retrieveApplicationVersionService_returnsServiceInstance() {
        var service = configuration.retrieveApplicationVersionService();

        assertThat(service).isNotNull().isInstanceOf(RetrieveApplicationVersionService.class);
    }
}
