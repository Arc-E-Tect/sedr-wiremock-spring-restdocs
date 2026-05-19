package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DependencyVersionTest {

    @Test
    void constructor_setsNameAndCode() {
        var version = new DependencyVersion("UserAccount", 1.0);

        assertThat(version.getName()).isEqualTo("UserAccount");
        assertThat(version.getCode()).isEqualTo(1.0);
    }

    @Test
    void constructor_allowsNullName() {
        var version = new DependencyVersion(null, null);

        assertThat(version.getName()).isNull();
        assertThat(version.getCode()).isNull();
    }
}
