package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.port.out;

import com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.model.DependencyVersion;

import java.util.Map;

public interface RetrieveDependencyVersionPort {

    Map<String, DependencyVersion> retrieveAllDependencyVersions();
}
