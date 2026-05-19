package com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.adapters.in.web;

import com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.adapters.in.web.dto.VersionResponse;
import com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.application.domain.model.ApplicationVersion;
import com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.application.port.in.RetrieveApplicationVersionUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/", produces = "application/json")
public class SocialNetworkController {

    private final RetrieveApplicationVersionUseCase retrieveApplicationVersionUseCase;

    public SocialNetworkController(RetrieveApplicationVersionUseCase retrieveApplicationVersionUseCase) {
        this.retrieveApplicationVersionUseCase = retrieveApplicationVersionUseCase;
    }

    @GetMapping
    public VersionResponse index() {
        log.info("index() called");

        ApplicationVersion version = retrieveApplicationVersionUseCase.retrieveApplicationVersion();
        return toDto(version);
    }

    private VersionResponse toDto(ApplicationVersion version) {
        return VersionResponse.builder()
                .versionName(version.getName())
                .versionCode(version.getCode())
                .build();
    }
}
