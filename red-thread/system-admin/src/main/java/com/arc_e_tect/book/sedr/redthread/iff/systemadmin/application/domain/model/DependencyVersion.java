package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.model;

public class DependencyVersion {

    private final String name;
    private final Double code;

    public DependencyVersion(String name, Double code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public Double getCode() {
        return code;
    }
}
