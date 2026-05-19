package com.arc_e_tect.book.sedr.redthread.iff.relationship.adapters.in.web;

import com.arc_e_tect.book.sedr.redthread.iff.relationship.application.domain.model.ApplicationVersion;
import com.arc_e_tect.book.sedr.redthread.iff.relationship.application.port.in.RetrieveApplicationVersionUseCase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RelationshipControllerTest {

    private final RetrieveApplicationVersionUseCase useCase = mock(RetrieveApplicationVersionUseCase.class);
    private final RelationshipController controller = new RelationshipController(useCase);

    @Test
    void index_returnsVersionResponse_withNameAndCode() {
        when(useCase.retrieveApplicationVersion()).thenReturn(new ApplicationVersion("Relationship", 1.0));

        var response = controller.index();

        assertThat(response.getVersionName()).isEqualTo("Relationship");
        assertThat(response.getVersionCode()).isEqualTo(1.0);
    }
}
