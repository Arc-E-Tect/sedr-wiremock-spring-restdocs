package com.arc_e_tect.book.sedr.redthread.iff.relationship.application.domain.model;

import java.util.Objects;

public class ApplicationVersion {

    private final String name;
    private final double code;

    public ApplicationVersion(String name, double code) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public double getCode() {
        return code;
    }
}
