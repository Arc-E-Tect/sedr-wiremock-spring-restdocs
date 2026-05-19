package com.arc_e_tect.book.sedr.redthread.iff.relationship.application.domain.service;

import com.arc_e_tect.book.sedr.redthread.iff.relationship.application.domain.model.ApplicationVersion;
import com.arc_e_tect.book.sedr.redthread.iff.relationship.application.port.in.RetrieveApplicationVersionUseCase;

public class RetrieveApplicationVersionService implements RetrieveApplicationVersionUseCase {

    @Override
    public ApplicationVersion retrieveApplicationVersion() {
        return new ApplicationVersion("Relationship", 1.0);
    }
}
