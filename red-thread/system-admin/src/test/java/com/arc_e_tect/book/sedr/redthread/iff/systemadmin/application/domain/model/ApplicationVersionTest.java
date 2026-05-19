package com.arc_e_tect.book.sedr.redthread.iff.systemadmin.application.domain.model;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class ApplicationVersionTest {

    @Test
    void constructor_setsNameAndCode() {
        var version = new ApplicationVersion("IFF", 1.0, null);

        assertThat(version.getName()).isEqualTo("IFF");
        assertThat(version.getCode()).isEqualTo(1.0);
    }

    @Test
    void constructor_setsDependencies() {
        var deps = Map.of("user-account", new DependencyVersion("UserAccount", 1.0));
        var version = new ApplicationVersion("IFF", 1.0, deps);

        assertThat(version.getDependencies()).containsKey("user-account");
    }

    @Test
    void constructor_throwsNullPointerException_whenNameIsNull() {
        assertThatNullPointerException()
                .isThrownBy(() -> new ApplicationVersion(null, 1.0, null))
                .withMessage("name must not be null");
    }
}
