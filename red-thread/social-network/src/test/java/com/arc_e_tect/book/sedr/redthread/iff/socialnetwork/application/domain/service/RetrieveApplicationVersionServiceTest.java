package com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.application.domain.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RetrieveApplicationVersionServiceTest {

    private final RetrieveApplicationVersionService service = new RetrieveApplicationVersionService();

    @Test
    void retrieveApplicationVersion_returnsSocialNetworkVersion() {
        var result = service.retrieveApplicationVersion();

        assertThat(result.getName()).isEqualTo("SocialNetwork");
        assertThat(result.getCode()).isEqualTo(1.0);
    }
}
