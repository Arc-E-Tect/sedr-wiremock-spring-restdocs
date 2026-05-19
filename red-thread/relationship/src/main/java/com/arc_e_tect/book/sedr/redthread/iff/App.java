package com.arc_e_tect.book.sedr.redthread.iff;

import com.arc_e_tect.sedr.utils.jacoco.marker.ExcludeFromJacocoGeneratedCodeCoverage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.arc_e_tect.book"})
@ExcludeFromJacocoGeneratedCodeCoverage(justification = "Spring Boot entry point — not unit-testable")
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);

        log.info("Spring Boot application started successfully.");
        log.debug("This is a debug message.");
    }
}
