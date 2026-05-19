package com.arc_e_tect.book.sedr.redthread.iff.useraccount.application.domain.service;

import com.arc_e_tect.book.sedr.redthread.iff.useraccount.application.domain.model.ApplicationVersion;
import com.arc_e_tect.book.sedr.redthread.iff.useraccount.application.port.in.RetrieveApplicationVersionUseCase;

public class RetrieveApplicationVersionService implements RetrieveApplicationVersionUseCase {

    @Override
    public ApplicationVersion retrieveApplicationVersion() {
        return new ApplicationVersion("UserAccount", 1.0);
    }
}
