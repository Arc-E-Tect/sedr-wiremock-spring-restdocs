package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.adapters.out.web;

import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.adapters.out.web.dto.DependencyVersionResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DependencyVersionHttpAdapterTest {

    @SuppressWarnings("rawtypes")
    private RestClient.RequestHeadersUriSpec requestUriSpec;
    @SuppressWarnings("rawtypes")
    private RestClient.RequestHeadersSpec requestSpec;
    private RestClient.ResponseSpec responseSpec;
    private DependencyVersionHttpAdapter adapter;

    @BeforeEach
    @SuppressWarnings({"unchecked", "rawtypes"})
    void setUp() {
        RestClient restClient = mock(RestClient.class);
        requestUriSpec = mock(RestClient.RequestHeadersUriSpec.class);
        requestSpec = mock(RestClient.RequestHeadersSpec.class);
        responseSpec = mock(RestClient.ResponseSpec.class);

        when(restClient.get()).thenReturn(requestUriSpec);
        when(requestUriSpec.uri(anyString())).thenReturn(requestSpec);
        when(requestSpec.retrieve()).thenReturn(responseSpec);

        adapter = new DependencyVersionHttpAdapter(
                "http://user-account", "http://auth-server",
                "http://relationship", "http://social-network",
                restClient);
    }

    @Test
    void retrieveAllDependencyVersions_returnsVersionForEachService() {
        when(responseSpec.body(DependencyVersionResponse.class))
                .thenReturn(new DependencyVersionResponse("STUB", 1.0));

        var result = adapter.retrieveAllDependencyVersions();

        assertThat(result).containsKeys("user-account", "auth-server", "relationship", "social-network");
        assertThat(result.get("user-account").getName()).isEqualTo("STUB");
        assertThat(result.get("user-account").getCode()).isEqualTo(1.0);
    }

    @Test
    void retrieveAllDependencyVersions_returnsNullMarker_whenRestClientThrows() {
        when(responseSpec.body(DependencyVersionResponse.class))
                .thenThrow(new RuntimeException("connection refused"));

        var result = adapter.retrieveAllDependencyVersions();

        assertThat(result).containsKeys("user-account", "auth-server", "relationship", "social-network");
        result.values().forEach(v -> {
            assertThat(v.getName()).isNull();
            assertThat(v.getCode()).isNull();
        });
    }

    @Test
    void retrieveAllDependencyVersions_returnsNullMarker_whenResponseBodyIsNull() {
        when(responseSpec.body(DependencyVersionResponse.class)).thenReturn(null);

        var result = adapter.retrieveAllDependencyVersions();

        assertThat(result).containsKeys("user-account", "auth-server", "relationship", "social-network");
        result.values().forEach(v -> {
            assertThat(v.getName()).isNull();
            assertThat(v.getCode()).isNull();
        });
    }
}
