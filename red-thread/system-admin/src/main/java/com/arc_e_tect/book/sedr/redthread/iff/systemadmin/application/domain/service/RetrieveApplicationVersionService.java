package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.service;

import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.model.ApplicationVersion;
import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.port.in.RetrieveApplicationVersionUseCase;
import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.port.out.RetrieveDependencyVersionPort;

public class RetrieveApplicationVersionService implements RetrieveApplicationVersionUseCase {

    private final RetrieveDependencyVersionPort dependencyVersionPort;

    public RetrieveApplicationVersionService(RetrieveDependencyVersionPort dependencyVersionPort) {
        this.dependencyVersionPort = dependencyVersionPort;
    }

    @Override
    public ApplicationVersion retrieveApplicationVersion() {
        return new ApplicationVersion("IFF", 1.0, dependencyVersionPort.retrieveAllDependencyVersions());
    }
}
