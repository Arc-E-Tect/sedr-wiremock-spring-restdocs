package com.arc_e_tect.book.sedr.redthread.iff.authserver.adapters.in.web;

import com.arc_e_tect.book.sedr.redthread.iff.authserver.application.domain.model.ApplicationVersion;
import com.arc_e_tect.book.sedr.redthread.iff.authserver.application.port.in.RetrieveApplicationVersionUseCase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServerControllerTest {

    private final RetrieveApplicationVersionUseCase useCase = mock(RetrieveApplicationVersionUseCase.class);
    private final AuthServerController controller = new AuthServerController(useCase);

    @Test
    void index_returnsVersionResponse_withNameAndCode() {
        when(useCase.retrieveApplicationVersion()).thenReturn(new ApplicationVersion("AuthServer", 1.0));

        var response = controller.index();

        assertThat(response.getVersionName()).isEqualTo("AuthServer");
        assertThat(response.getVersionCode()).isEqualTo(1.0);
    }
}
