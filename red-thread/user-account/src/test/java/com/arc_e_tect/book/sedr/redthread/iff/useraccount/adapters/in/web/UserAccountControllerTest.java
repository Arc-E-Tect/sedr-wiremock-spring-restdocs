package com.arc_e_tect.book.sedr.redthread.iff.useraccount.adapters.in.web;

import com.arc_e_tect.book.sedr.redthread.iff.useraccount.application.domain.model.ApplicationVersion;
import com.arc_e_tect.book.sedr.redthread.iff.useraccount.application.port.in.RetrieveApplicationVersionUseCase;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserAccountControllerTest {

    private final RetrieveApplicationVersionUseCase useCase = mock(RetrieveApplicationVersionUseCase.class);
    private final UserAccountController controller = new UserAccountController(useCase);

    @Test
    void index_returnsVersionResponse_withNameAndCode() {
        when(useCase.retrieveApplicationVersion()).thenReturn(new ApplicationVersion("UserAccount", 1.0));

        var response = controller.index();

        assertThat(response.getVersionName()).isEqualTo("UserAccount");
        assertThat(response.getVersionCode()).isEqualTo(1.0);
    }
}
