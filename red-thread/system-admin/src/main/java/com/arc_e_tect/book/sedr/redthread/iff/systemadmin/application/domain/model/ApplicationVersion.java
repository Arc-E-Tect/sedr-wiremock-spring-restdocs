package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.model;

import java.util.Map;
import java.util.Objects;

public class ApplicationVersion {

    private final String name;
    private final double code;
    private final Map<String, DependencyVersion> dependencies;

    public ApplicationVersion(String name, double code, Map<String, DependencyVersion> dependencies) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = code;
        this.dependencies = dependencies;
    }

    public String getName() {
        return name;
    }

    public double getCode() {
        return code;
    }

    public Map<String, DependencyVersion> getDependencies() {
        return dependencies;
    }
}
