package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.adapters.in.web;

import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.model.ApplicationVersion;
import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.model.DependencyVersion;
import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.port.in.RetrieveApplicationVersionUseCase;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SystemAdminControllerTest {

    private final RetrieveApplicationVersionUseCase useCase = mock(RetrieveApplicationVersionUseCase.class);
    private final SystemAdminController controller = new SystemAdminController(useCase);

    @Test
    void index_returnsVersionResponse_withNameAndCode() {
        when(useCase.retrieveApplicationVersion()).thenReturn(new ApplicationVersion("IFF", 1.0, Map.of()));

        var response = controller.index();

        assertThat(response.getVersionName()).isEqualTo("IFF");
        assertThat(response.getVersionCode()).isEqualTo(1.0);
    }

    @Test
    void index_returnsVersionResponse_withDependencies() {
        var deps = new LinkedHashMap<String, DependencyVersion>();
        deps.put("user-account", new DependencyVersion("UserAccount", 1.0));
        deps.put("auth-server", new DependencyVersion(null, null));
        when(useCase.retrieveApplicationVersion()).thenReturn(new ApplicationVersion("IFF", 1.0, deps));

        var response = controller.index();

        assertThat(response.getDependencies()).containsKey("user-account");
        assertThat(response.getDependencies().get("user-account").getVersionName()).isEqualTo("UserAccount");
        assertThat(response.getDependencies().get("user-account").getVersionCode()).isEqualTo(1.0);
        assertThat(response.getDependencies().get("auth-server").getVersionName()).isNull();
        assertThat(response.getDependencies().get("auth-server").getVersionCode()).isNull();
    }

    @Test
    void index_returnsVersionResponse_withNullDependencies_whenModelHasNullDeps() {
        when(useCase.retrieveApplicationVersion()).thenReturn(new ApplicationVersion("IFF", 1.0, null));

        var response = controller.index();

        assertThat(response.getDependencies()).isNull();
    }
}
