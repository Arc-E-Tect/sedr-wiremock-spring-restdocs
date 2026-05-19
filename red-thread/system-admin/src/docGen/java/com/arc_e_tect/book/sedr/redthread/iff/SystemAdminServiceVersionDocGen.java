package com.arc_e_tect.book.sedr.redthread.iff;

import com.arc_e_tect.book.sedr.DocumentationGenerator;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.cloud.contract.wiremock.restdocs.WireMockWebTestClient;
import org.springframework.http.MediaType;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;

@Slf4j
@NoArgsConstructor
public class SystemAdminServiceVersionDocGen extends DocumentationGenerator {

    @BeforeEach
    void stubForVersion() {
        wireMockServer.stubFor(
                get(urlEqualTo("/"))
                        .withHeader("Accept", equalTo("application/json"))
                        .willReturn(aResponse()
                                .withStatus(200)
                                .withHeader("Content-Type", "application/json")
                                .withBody("""
                                        {
                                          "versionName": "IFF",
                                          "versionCode": 1.0,
                                          "dependencies": {
                                            "user-account":   {"versionName": "IFF", "versionCode": 1.0},
                                            "auth-server":    {"versionName": "IFF", "versionCode": 1.0},
                                            "relationship":   {"versionName": "IFF", "versionCode": 1.0},
                                            "social-network": {"versionName": "IFF", "versionCode": 1.0}
                                          }
                                        }
                                        """)));
    }

    @Test
    void retrieveVersionInformation() {
        this.docGeneratorClient.get().uri("/")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(WireMockWebTestClient.verify())
                .consumeWith(document("{class-name}/{method-name}/get-version"));
    }
}
