package com.arc_e_tect.book.sedr.redthread.iff.useraccount.configuration;

import com.arc_e_tect.book.sedr.redthread.iff.useraccount.application.domain.service.RetrieveApplicationVersionService;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserAccountConfigurationTest {

    private final UserAccountConfiguration configuration = new UserAccountConfiguration();

    @Test
    void retrieveApplicationVersionService_returnsServiceInstance() {
        var service = configuration.retrieveApplicationVersionService();

        assertThat(service).isNotNull().isInstanceOf(RetrieveApplicationVersionService.class);
    }
}
