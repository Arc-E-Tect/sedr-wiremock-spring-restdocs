package com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.application.domain.service;

import com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.application.domain.model.ApplicationVersion;
import com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.application.port.in.RetrieveApplicationVersionUseCase;

public class RetrieveApplicationVersionService implements RetrieveApplicationVersionUseCase {

    @Override
    public ApplicationVersion retrieveApplicationVersion() {
        return new ApplicationVersion("SocialNetwork", 1.0);
    }
}
