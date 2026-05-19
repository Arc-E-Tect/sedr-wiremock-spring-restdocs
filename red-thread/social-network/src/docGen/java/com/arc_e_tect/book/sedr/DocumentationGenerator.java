package com.arc_e_tect.book.sedr;

import com.arc_e_tect.book.sedr.redthread.iff.App;
import com.github.tomakehurst.wiremock.WireMockServer;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.restdocs.WireMockSnippet;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.webtestclient.WebTestClientRestDocumentationConfigurer;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.context.WebApplicationContext;
import org.wiremock.spring.ConfigureWireMock;
import org.wiremock.spring.EnableWireMock;
import org.wiremock.spring.InjectWireMock;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;

@Slf4j
@ExtendWith({ RestDocumentationExtension.class, SpringExtension.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT, classes = App.class,
        properties = {"server.port=9593"})
@ActiveProfiles({"docGen"})
@EnableWireMock({
        @ConfigureWireMock(
                name = DocumentationGeneratorConstants.WIREMOCK_SERVER_NAME,
                port = 9584,
                usePortFromPredefinedPropertyIfFound = true,
                filesUnderDirectory = "wiremock"
        )
})
@NoArgsConstructor
public class DocumentationGenerator implements DocumentationGeneratorConstants {

    @InjectWireMock(DocumentationGeneratorConstants.WIREMOCK_SERVER_NAME)
    protected WireMockServer wireMockServer;

    protected String localMachineIp = getLocalMachineIp();

    @Value("${wiremock.server.baseUrl}")
    protected String wireMockUrl;

    @Value("${response.timeout}")
    protected Integer responseTimeout;

    protected WebTestClient docGeneratorClient;

    @BeforeEach
    void setUp(WebApplicationContext webApplicationContext, RestDocumentationContextProvider restDocumentation) {
        log.info("Setting up Documentation Generator...");

        this.wireMockServer.resetAll();

        WebTestClientRestDocumentationConfigurer restDocsConfigurer = documentationConfiguration(restDocumentation);
        restDocsConfigurer.snippets().withAdditionalDefaults(new WireMockSnippet());
        restDocsConfigurer.operationPreprocessors();

        this.docGeneratorClient = WebTestClient.bindToServer()
                .baseUrl(wireMockUrl)
                .defaultHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("Accept", MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader("User-Agent", "API-Contract-Validator")
                .defaultHeader("X-Forwarded-For", localMachineIp)
                .filter(restDocsConfigurer)
                .responseTimeout(Duration.ofMillis(responseTimeout))
                .build();
    }

    private static String getLocalMachineIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            log.error("Unable to get local host address", e);
            return "127.0.0.1";
        }
    }
}
