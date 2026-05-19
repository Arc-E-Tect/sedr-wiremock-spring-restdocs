package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.configuration;

import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.service.RetrieveApplicationVersionService;
import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.port.out.RetrieveDependencyVersionPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.net.http.HttpClient;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

@Configuration
public class SystemAdminConfiguration {

    @Bean
    public RetrieveApplicationVersionService retrieveApplicationVersionService(RetrieveDependencyVersionPort dependencyVersionPort) {
        return new RetrieveApplicationVersionService(dependencyVersionPort);
    }

    @Bean
    public SSLContext trustAllSslContext() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, new TrustManager[]{new TrustAllX509TrustManager()}, null);
        return sslContext;
    }

    @Bean
    public RestClient dependencyVersionRestClient(SSLContext trustAllSslContext) {
        HttpClient jdkClient = HttpClient.newBuilder()
                .sslContext(trustAllSslContext)
                .build();
        return RestClient.builder()
                .requestFactory(new JdkClientHttpRequestFactory(jdkClient))
                .build();
    }

    static class TrustAllX509TrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {}

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
