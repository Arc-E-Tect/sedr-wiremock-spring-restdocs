package com.arc_e_tect.book.sedr.redthread.iff.relationship.application.domain.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RetrieveApplicationVersionServiceTest {

    private final RetrieveApplicationVersionService service = new RetrieveApplicationVersionService();

    @Test
    void retrieveApplicationVersion_returnsRelationshipVersion() {
        var result = service.retrieveApplicationVersion();

        assertThat(result.getName()).isEqualTo("Relationship");
        assertThat(result.getCode()).isEqualTo(1.0);
    }
}
