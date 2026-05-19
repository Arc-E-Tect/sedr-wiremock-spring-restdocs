package com.arc_e_tect.book.sedr.redthread.iff.authserver.application.domain.service;

import com.arc_e_tect.book.sedr.redthread.iff.authserver.application.domain.model.ApplicationVersion;
import com.arc_e_tect.book.sedr.redthread.iff.authserver.application.port.in.RetrieveApplicationVersionUseCase;

public class RetrieveApplicationVersionService implements RetrieveApplicationVersionUseCase {

    @Override
    public ApplicationVersion retrieveApplicationVersion() {
        return new ApplicationVersion("AuthServer", 1.0);
    }
}
