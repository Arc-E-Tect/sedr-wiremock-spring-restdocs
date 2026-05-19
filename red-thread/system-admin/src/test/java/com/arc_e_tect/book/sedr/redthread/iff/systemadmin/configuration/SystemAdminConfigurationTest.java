package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.configuration;

import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.service.RetrieveApplicationVersionService;
import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.port.out.RetrieveDependencyVersionPort;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SystemAdminConfigurationTest {

    private final SystemAdminConfiguration configuration = new SystemAdminConfiguration();

    @Test
    void retrieveApplicationVersionService_returnsServiceInstance() {
        RetrieveDependencyVersionPort port = mock(RetrieveDependencyVersionPort.class);
        when(port.retrieveAllDependencyVersions()).thenReturn(Map.of());

        var service = configuration.retrieveApplicationVersionService(port);

        assertThat(service).isNotNull().isInstanceOf(RetrieveApplicationVersionService.class);
    }

    @Test
    void trustAllSslContext_createsContextForTls() throws Exception {
        var ctx = configuration.trustAllSslContext();

        assertThat(ctx).isNotNull();
        assertThat(ctx.getProtocol()).isEqualTo("TLS");
    }

    @Test
    void dependencyVersionRestClient_buildsRestClient() throws Exception {
        var client = configuration.dependencyVersionRestClient(configuration.trustAllSslContext());

        assertThat(client).isNotNull();
    }

    @Test
    void trustAllX509TrustManager_acceptsAllCertificatesAndReturnsEmptyIssuers() {
        var tm = new SystemAdminConfiguration.TrustAllX509TrustManager();

        assertThatCode(() -> tm.checkClientTrusted(null, null)).doesNotThrowAnyException();
        assertThatCode(() -> tm.checkServerTrusted(null, null)).doesNotThrowAnyException();
        assertThat(tm.getAcceptedIssuers()).isEmpty();
    }
}
