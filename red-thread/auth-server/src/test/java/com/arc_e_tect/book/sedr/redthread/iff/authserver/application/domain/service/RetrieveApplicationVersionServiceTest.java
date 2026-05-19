package com.arc_e_tect.book.sedr.redthread.iff.authserver.application.domain.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RetrieveApplicationVersionServiceTest {

    private final RetrieveApplicationVersionService service = new RetrieveApplicationVersionService();

    @Test
    void retrieveApplicationVersion_returnsAuthServerVersion() {
        var result = service.retrieveApplicationVersion();

        assertThat(result.getName()).isEqualTo("AuthServer");
        assertThat(result.getCode()).isEqualTo(1.0);
    }
}
