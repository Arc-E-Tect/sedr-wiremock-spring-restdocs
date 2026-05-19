package com.arc_e_tect.book.sedr.redthread.iff.authserver.configuration;

import com.arc_e_tect.book.sedr.redthread.iff.authserver.application.domain.service.RetrieveApplicationVersionService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AuthServerConfigurationTest {

    private final AuthServerConfiguration configuration = new AuthServerConfiguration();

    @Test
    void retrieveApplicationVersionService_returnsServiceInstance() {
        var service = configuration.retrieveApplicationVersionService();

        assertThat(service).isNotNull().isInstanceOf(RetrieveApplicationVersionService.class);
    }
}
