package com.arc_e_tect.book.sedr.redthread.iff.authserver.application.port.in;

import com.arc_e_tect.book.sedr.redthread.iff.authserver.application.domain.model.ApplicationVersion;

public interface RetrieveApplicationVersionUseCase {
    ApplicationVersion retrieveApplicationVersion();
}
