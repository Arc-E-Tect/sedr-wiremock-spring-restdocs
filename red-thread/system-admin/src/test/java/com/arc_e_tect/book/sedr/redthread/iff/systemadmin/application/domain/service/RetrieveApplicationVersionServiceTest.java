package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.service;

import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.model.DependencyVersion;
import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.port.out.RetrieveDependencyVersionPort;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RetrieveApplicationVersionServiceTest {

    private final RetrieveDependencyVersionPort port = mock(RetrieveDependencyVersionPort.class);
    private final RetrieveApplicationVersionService service = new RetrieveApplicationVersionService(port);

    @Test
    void retrieveApplicationVersion_returnsIffVersion() {
        when(port.retrieveAllDependencyVersions()).thenReturn(Map.of());

        var result = service.retrieveApplicationVersion();

        assertThat(result.getName()).isEqualTo("IFF");
        assertThat(result.getCode()).isEqualTo(1.0);
    }

    @Test
    void retrieveApplicationVersion_includesDependenciesFromPort() {
        var deps = Map.of("user-account", new DependencyVersion("UserAccount", 1.0));
        when(port.retrieveAllDependencyVersions()).thenReturn(deps);

        var result = service.retrieveApplicationVersion();

        assertThat(result.getDependencies()).containsKey("user-account");
        assertThat(result.getDependencies().get("user-account").getName()).isEqualTo("UserAccount");
    }
}
