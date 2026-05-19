package com.arc_e_tect.book.sedr.redthread.iff.socialnetwork.application.domain.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class ApplicationVersionTest {

    @Test
    void constructor_setsNameAndCode() {
        var version = new ApplicationVersion("SocialNetwork", 1.0);

        assertThat(version.getName()).isEqualTo("SocialNetwork");
        assertThat(version.getCode()).isEqualTo(1.0);
    }

    @Test
    void constructor_throwsNullPointerException_whenNameIsNull() {
        assertThatNullPointerException()
                .isThrownBy(() -> new ApplicationVersion(null, 1.0))
                .withMessage("name must not be null");
    }
}
