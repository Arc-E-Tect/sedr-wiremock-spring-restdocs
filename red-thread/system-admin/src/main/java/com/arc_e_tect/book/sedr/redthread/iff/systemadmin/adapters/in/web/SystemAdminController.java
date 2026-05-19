package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.adapters.in.web;

import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.adapters.in.web.dto.DependencyVersionDto;
import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.adapters.in.web.dto.VersionResponse;
import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.model.ApplicationVersion;
import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.port.in.RetrieveApplicationVersionUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "/", produces = "application/json")
public class SystemAdminController {

    private final RetrieveApplicationVersionUseCase retrieveApplicationVersionUseCase;

    public SystemAdminController(RetrieveApplicationVersionUseCase retrieveApplicationVersionUseCase) {
        this.retrieveApplicationVersionUseCase = retrieveApplicationVersionUseCase;
    }

    @GetMapping
    public VersionResponse index() {
        log.info("index() called");

        ApplicationVersion version = retrieveApplicationVersionUseCase.retrieveApplicationVersion();
        return toDto(version);
    }

    private VersionResponse toDto(ApplicationVersion version) {
        Map<String, DependencyVersionDto> deps = null;
        if (version.getDependencies() != null) {
            Map<String, DependencyVersionDto> depsMap = new LinkedHashMap<>();
            version.getDependencies().forEach((k, v) ->
                    depsMap.put(k, DependencyVersionDto.builder()
                            .versionName(v.getName())
                            .versionCode(v.getCode())
                            .build()));
            deps = depsMap;
        }
        return VersionResponse.builder()
                .versionName(version.getName())
                .versionCode(version.getCode())
                .dependencies(deps)
                .build();
    }
}
