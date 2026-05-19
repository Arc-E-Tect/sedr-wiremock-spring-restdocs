package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.adapters.out.web;

import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.adapters.out.web.dto.DependencyVersionResponse;
import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.model.DependencyVersion;
import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.port.out.RetrieveDependencyVersionPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Component
public class DependencyVersionHttpAdapter implements RetrieveDependencyVersionPort {

    private final Map<String, String> serviceUrls;
    private final RestClient restClient;

    public DependencyVersionHttpAdapter(
            @Value("${iff.services.user-account.url}") String userAccountUrl,
            @Value("${iff.services.auth-server.url}") String authServerUrl,
            @Value("${iff.services.relationship.url}") String relationshipUrl,
            @Value("${iff.services.social-network.url}") String socialNetworkUrl,
            RestClient restClient) {
        this.serviceUrls = new LinkedHashMap<>();
        this.serviceUrls.put("user-account", userAccountUrl);
        this.serviceUrls.put("auth-server", authServerUrl);
        this.serviceUrls.put("relationship", relationshipUrl);
        this.serviceUrls.put("social-network", socialNetworkUrl);
        this.restClient = restClient;
    }

    @Override
    public Map<String, DependencyVersion> retrieveAllDependencyVersions() {
        Map<String, DependencyVersion> result = new LinkedHashMap<>();
        serviceUrls.forEach((name, url) -> result.put(name, fetchVersion(name, url)));
        return result;
    }

    private DependencyVersion fetchVersion(String serviceName, String url) {
        try {
            DependencyVersionResponse response = restClient.get()
                    .uri(url + "/")
                    .retrieve()
                    .body(DependencyVersionResponse.class);
            if (response == null) {
                log.warn("Received null response from service '{}' at {}", serviceName, url);
                return new DependencyVersion(null, null);
            }
            return new DependencyVersion(response.getVersionName(), response.getVersionCode());
        } catch (Exception e) {
            log.warn("Could not retrieve version from service '{}' at {}: {}", serviceName, url, e.getMessage());
            return new DependencyVersion(null, null);
        }
    }

}
