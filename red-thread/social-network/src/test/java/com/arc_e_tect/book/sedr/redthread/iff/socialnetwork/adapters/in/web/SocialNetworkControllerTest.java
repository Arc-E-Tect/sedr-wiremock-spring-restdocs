package com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.adapters.in.web;

import com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.application.domain.model.ApplicationVersion;
import com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.application.port.in.RetrieveApplicationVersionUseCase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SocialNetworkControllerTest {

    private final RetrieveApplicationVersionUseCase useCase = mock(RetrieveApplicationVersionUseCase.class);
    private final SocialNetworkController controller = new SocialNetworkController(useCase);

    @Test
    void index_returnsVersionResponse_withNameAndCode() {
        when(useCase.retrieveApplicationVersion()).thenReturn(new ApplicationVersion("SocialNetwork", 1.0));

        var response = controller.index();

        assertThat(response.getVersionName()).isEqualTo("SocialNetwork");
        assertThat(response.getVersionCode()).isEqualTo(1.0);
    }
}
